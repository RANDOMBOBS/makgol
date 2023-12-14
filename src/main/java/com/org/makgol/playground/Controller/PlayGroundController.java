package com.org.makgol.playground.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/playground")
public class PlayGroundController {

    @GetMapping({ "/", "" })
    public String showplay() {
        String nextPage = "jsp/playground/playground";
        return nextPage;
    }

}