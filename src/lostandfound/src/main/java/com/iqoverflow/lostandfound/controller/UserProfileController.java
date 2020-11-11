package com.iqoverflow.lostandfound.controller;


import com.iqoverflow.lostandfound.domain.User;
import com.iqoverflow.lostandfound.service.UserProfileservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
public class UserProfileController {
    @Autowired
    private UserProfileservice userProfileservice;
    //获取用户个人信息
    @GetMapping("/getuserProfile")
    public User getUserProfile(Model model, HttpSession session) {
        String openid = (String) session.getAttribute("openid");
        User userProfile = userProfileservice.getUserProfile(openid);
        model.addAttribute("userProfile", userProfile);
        return userProfile;
    }

}
