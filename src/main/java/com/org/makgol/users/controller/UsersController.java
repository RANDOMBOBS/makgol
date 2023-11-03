package com.org.makgol.users.controller;

import com.org.makgol.stores.vo.StoreRequestVo;
import com.org.makgol.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @PostMapping("/user/join")
    public void usersJoin(@RequestBody List<StoreRequestVo> storeRequestVoList) throws Exception{
        usersService.usersJoin(storeRequestVoList);
    }
}
