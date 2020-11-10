package com.iqoverflow.lostandfound.controller;

import com.iqoverflow.lostandfound.domain.Reason;
import com.iqoverflow.lostandfound.service.ReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/reason")
public class ReasonController {
    @Autowired
    ReasonService reasonService;

    // 申请联系
    @PostMapping("/appliesForContact")
    public Boolean appliesForContact(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Reason reason = new Reason();
        reason.setpID((String) map.get("pID"));
        reason.setfID((String) session.getAttribute("openid"));
        reason.settID((String) map.get("tID"));
        reason.setMessage((String) map.get("message"));
        reason.setState(0);
        // 提交申请，返回提交结果
        int result = reasonService.appliesForContact(reason);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    //返回“我申请的”
    @GetMapping("/myapplies")
    public Reason[] myApplies(HttpServletRequest request){
        HttpSession session = request.getSession();
        String fID = (String)session.getAttribute("uID");
        if(fID == null){
            
        }
        return reasonService.myApplies(fID);
    }

}
