package com.iqoverflow.lostandfound.controller;

import com.iqoverflow.lostandfound.domain.Reason;
import com.iqoverflow.lostandfound.service.ReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/reason")
public class ReasonController {
    @Autowired
    ReasonService reasonService;

    // 同意申请
    @PostMapping("/agreeApplies")
    public Map<String, Object> agreeApplies(@RequestBody Map<String, Object> info, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Reason reason = new Reason();
        reason.setpID((String) info.get("pID"));
        reason.setfID((String) info.get("fID"));
        reason.setState(1);
        reason.settID((String) session.getAttribute("openid"));
        reason.setMessage("");
        int result = reasonService.agreeApplies(reason);
        // 返回发布结果
        Map<String, Object> map = new HashMap<>();
        if (result == 1) {
            map.put("code",1);
            map.put("msg", "已同意");
        } else {
            map.put("code",0);
            map.put("msg", "同意失败，请稍后重试");
        }
        return map;
    }

    // 拒绝申请
    @PostMapping("/refuseApplies")
    public Map<String, Object> refuseApplies(@RequestBody Map<String, Object> info, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Reason reason = new Reason();
        reason.setpID((String) info.get("pID"));
        reason.setfID((String) info.get("fID"));
        reason.setState(2);
        reason.settID((String) session.getAttribute("openid"));
        reason.setMessage("");
        int result = reasonService.refuseApplies(reason);
        // 返回发布结果
        Map<String, Object> map = new HashMap<>();
        if (result == 1) {
            map.put("code",1);
            map.put("msg", "已拒绝");
        } else {
            map.put("code",0);
            map.put("msg", "拒绝失败，请稍后重试");
        }
        return map;
    }

    // 申请联系
    @PostMapping("/appliesForContact")
    public Map<String, Object> appliesForContact(@RequestBody Map<String, Object> info, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Reason reason = new Reason();
        reason.setpID((String) info.get("pID"));
        reason.setfID((String) session.getAttribute("openid"));
        reason.settID((String) info.get("tID"));
        reason.setMessage((String) info.get("message"));
        reason.setState(0);
        // 提交申请，返回提交结果
        int result = reasonService.appliesForContact(reason);
        // 返回发布结果
        Map<String, Object> map = new HashMap<>();
        if (result == 1) {
            map.put("code",1);
            map.put("msg", "发布申请成功");
        } else {
            map.put("code",0);
            map.put("msg", "发布申请失败");
        }
        return map;
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
