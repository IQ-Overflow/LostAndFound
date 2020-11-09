package com.iqoverflow.lostandfound.controller;

import com.iqoverflow.lostandfound.domain.Reason;
import com.iqoverflow.lostandfound.service.ReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/reason")
public class ReasonController {
    @Autowired
    ReasonService reasonService;

    //返回“我申请的”
    @GetMapping("/myapplies")
    public Reason[] myApplies(HttpServletRequest request){
        HttpSession session = request.getSession();
        String fID = (String)session.getAttribute("uID");
        return reasonService.myApplies(fID);
    }

}
