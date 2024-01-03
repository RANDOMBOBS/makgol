package com.org.makgol.main.dao;


import com.org.makgol.category.vo.CategoryListVo;
import com.org.makgol.main.repository.MainRepository;
import com.org.makgol.users.vo.UserXy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class MainDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final MainRepository mainRepository;

    public List<CategoryListVo> selectAllCategory() throws DataAccessException {
        List<CategoryListVo> categoryVos = null;
        categoryVos = mainRepository.selectAllCategory();
        return categoryVos;
    }


    // 오늘의 메뉴 (no mybatis)
    public List<CategoryListVo> selectTodayMenu(String where) {
        String sql = "SELECT menus.menu, menus.photo, menus.photoPath \n" +
                "FROM menus \n" +
                "INNER JOIN stores ON menus.store_id = stores.id \n" +
                "WHERE stores.category = '"+ where +"' \n" +
                "LIMIT 0, 1000;";

        List<CategoryListVo> categorys = new ArrayList<CategoryListVo>();
        try {
            RowMapper<CategoryListVo> rowMapper = BeanPropertyRowMapper.newInstance(CategoryListVo.class);
            categorys = jdbcTemplate.query(sql, rowMapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categorys.size() > 0 ? categorys : null;
    }

    public List<CategoryListVo> selectTodayMenu(UserXy userXy) {
        //String sql = "SELECT menu,photo,photoPath FROM menus ";
        String sql = "SELECT menu, photo, photoPath\n" +
                "FROM menus\n" +
                "WHERE 1000 > ST_DISTANCE_SPHERE(\n" +
                "    POINT((SELECT longitude FROM stores WHERE id = menus.store_id), (SELECT latitude FROM stores WHERE id = menus.store_id)),\n" +
                "    POINT("+userXy.getX()+", "+userXy.getY()+")\n" +
                ")";
        List<CategoryListVo> categorys = new ArrayList<CategoryListVo>();
        try {
            RowMapper<CategoryListVo> rowMapper = BeanPropertyRowMapper.newInstance(CategoryListVo.class);
            categorys = jdbcTemplate.query(sql, rowMapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categorys.size() > 0 ? categorys : null;
    }

    // Top5 메뉴
    public List<CategoryListVo> selectTopMenu(UserXy userXy) {
        String sql = "SELECT stores.name, stores.photo\n" +
                "FROM stores\n" +
                "LEFT JOIN (\n" +
                "    SELECT store_id, COUNT(*) as like_count\n" +
                "    FROM store_likes\n" +
                "    GROUP BY store_id\n" +
                ") like_counts ON stores.id = like_counts.store_id\n" +
                "WHERE ST_DISTANCE_SPHERE(\n" +
                "    POINT(stores.longitude, stores.latitude),\n" +
                "    POINT("+userXy.getX()+", "+userXy.getY()+")\n" +
                ") <= 1000\n" +
                "ORDER BY like_counts.like_count DESC;";
        List<CategoryListVo> categorys = new ArrayList<CategoryListVo>();
        try {
            RowMapper<CategoryListVo> rowMapper = BeanPropertyRowMapper.newInstance(CategoryListVo.class);
            categorys = jdbcTemplate.query(sql, rowMapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categorys.size() > 0 ? categorys : null;
    }

}
