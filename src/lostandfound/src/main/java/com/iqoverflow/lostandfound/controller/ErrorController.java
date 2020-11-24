package com.iqoverflow.lostandfound.controller;


import com.iqoverflow.lostandfound.domain.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class ErrorController {
    @GetMapping("/loginError")
    public Message errorMsg(){
        return new Message(false,"请重新登录");
    }

    @PostMapping("/loginError")
    public Message errorMsg2(){
        return new Message(false,"请重新登录");
    }
}
