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

    /**
     * 모든 카테고리를 조회하는 메서드입니다.
     *
     * @return 카테고리 목록
     * @throws DataAccessException 데이터 액세스 예외가 발생할 경우
     */
    public List<CategoryListVo> selectAllCategory() throws DataAccessException {
        List<CategoryListVo> categoryVos = null;
        categoryVos = mainRepository.selectAllCategory();
        return categoryVos;
    }

    /**
     * 특정 카테고리의 오늘의 메뉴를 조회하는 메서드입니다.
     * (MyBatis를 사용하지 않은 버전)
     *
     * @param where 특정 카테고리 조건
     * @return 오늘의 메뉴 목록
     */
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

    /**
     * 사용자의 위치를 기반으로 오늘의 메뉴를 조회하는 메서드입니다.
     *
     * @param userXy 사용자의 위치 정보를 담은 객체
     * @return 오늘의 메뉴 목록
     */
    public List<CategoryListVo> selectTodayMenu(UserXy userXy) {
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

    /**
     * 사용자의 위치를 기반으로 Top5 메뉴를 조회하는 메서드입니다.
     *
     * @param userXy 사용자의 위치 정보를 담은 객체
     * @return Top5 메뉴 목록
     */
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
