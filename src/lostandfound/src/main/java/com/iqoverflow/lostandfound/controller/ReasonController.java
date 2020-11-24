package com.iqoverflow.lostandfound.controller;

import com.iqoverflow.lostandfound.domain.Reason;
import com.iqoverflow.lostandfound.interceptor.AdminInterceptor;
import com.iqoverflow.lostandfound.listener.MySessionContext;
import com.iqoverflow.lostandfound.service.ReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/reason")
public class ReasonController {
    @Autowired
    ReasonService reasonService;

    private HttpSession session = null;

    @ModelAttribute
    public ModelAndView index(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
      /*  Cookie[] cookies = request.getCookies();
        HttpSession session = null;
        if(cookies == null){
            this.session = request.getSession();
            System.out.println("没有cookie！");
            return modelAndView;
        }
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("JSESSIONID")){
                System.out.println("获取了session!");
                session = MySessionContext.getSession(cookie.getValue());
                this.session =session;
            }
        }

        if(this.session == null){
            this.session = request.getSession();
        }*/
        session = AdminInterceptor.session;

        return modelAndView;
    }

    // 同意申请
    @PostMapping("/agreeApplies")
    public Map<String, Object> agreeApplies(@RequestBody Map<String, Object> info, HttpServletRequest request) {
        //HttpSession session = request.getSession();
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
        //HttpSession session = request.getSession();
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
        //HttpSession session = request.getSession();
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
    public Reason[] myApplies(){
        //HttpSession session = request.getSession();
        String fID = (String)session.getAttribute("openid");

        return reasonService.myApplies(fID);
    }

    //返回“向我申请的"
    @GetMapping("/receivedApplies")
    public Reason[] myReceivedApplies(){
        //HttpSession session = request.getSession();
        String tID = (String)session.getAttribute("openid");
        return reasonService.myReceivedApplies(tID);
    }


}
