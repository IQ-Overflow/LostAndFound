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

    // 同意申请
    @PostMapping("/agreeApplies")
    public Boolean agreeApplies(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Reason reason = new Reason();
        reason.setpID((String) map.get("pID"));
        reason.setfID((String) map.get("fID"));
        reason.setState(1);
        reason.settID((String) session.getAttribute("openid"));
        reason.setMessage("");
        int result = reasonService.agreeApplies(reason);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    // 拒绝申请
    @PostMapping("/refuseApplies")
    public Boolean refuseApplies(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Reason reason = new Reason();
        reason.setpID((String) map.get("pID"));
        reason.setfID((String) map.get("fID"));
        reason.setState(2);
        reason.settID((String) session.getAttribute("openid"));
        reason.setMessage("");
        int result = reasonService.refuseApplies(reason);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

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
    @GetMapping("/myApplies")
    public Reason[] myApplies(HttpServletRequest request){
        HttpSession session = request.getSession();
        String fID = (String)session.getAttribute("openid");

        return reasonService.myApplies(fID);
    }

    //返回“向我申请的"
    @GetMapping("/receivedApplies")
    public Reason[] myReceivedApplies(HttpServletRequest request){
        HttpSession session = request.getSession();
        String tID = (String)session.getAttribute("openid");
        return reasonService.myReceivedApplies(tID);
    }


}
