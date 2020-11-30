package com.iqoverflow.lostandfound.controller;

import com.iqoverflow.lostandfound.dao.UserProfiledao;
import com.iqoverflow.lostandfound.domain.Card;
import com.iqoverflow.lostandfound.domain.Others;
import com.iqoverflow.lostandfound.domain.Reason;
import com.iqoverflow.lostandfound.interceptor.AdminInterceptor;
import com.iqoverflow.lostandfound.listener.MySessionContext;
import com.iqoverflow.lostandfound.service.CardService;
import com.iqoverflow.lostandfound.service.OthersService;
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

    @Autowired
    CardService cardService;

    @Autowired
    OthersService othersService;

    @Autowired
    UserProfiledao userProfiledao;
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
        if (this.session == null) {
            this.session = request.getSession();
        }
        Reason reason = new Reason();
        reason.setpID((String) info.get("pID"));
        reason.setfID((String) info.get("fID"));
        reason.setState(1);
        reason.settID((String) this.session.getAttribute("openid"));
        reason.setMessage("");
        int result = reasonService.agreeApplies(reason);
        // 返回发布结果
        Map<String, Object> map = new HashMap<>();
        if (result == 1) {
            map.put("code", 1);
            map.put("msg", "已同意");
            if (((String) info.get("pID")).length() == 10){
                cardService.cancelCard((String) info.get("pID"));
            }
        } else {
            map.put("code", 0);
            map.put("msg", "同意失败，请稍后重试");
        }
        return map;
    }

    // 拒绝申请
    @PostMapping("/refuseApplies")
    public Map<String, Object> refuseApplies(@RequestBody Map<String, Object> info, HttpServletRequest request) {
        if (this.session == null) {
            this.session = request.getSession();
        }
        //HttpSession session = request.getSession();
        Reason reason = new Reason();
        reason.setpID((String) info.get("pID"));
        reason.setfID((String) info.get("fID"));
        reason.setState(2);
        reason.settID((String) this.session.getAttribute("openid"));
        reason.setMessage("");
        int result = reasonService.refuseApplies(reason);
        // 返回发布结果
        Map<String, Object> map = new HashMap<>();
        if (result == 1) {
            map.put("code", 1);
            map.put("msg", "已拒绝");
        } else {
            map.put("code", 0);
            map.put("msg", "拒绝失败，请稍后重试");
        }
        return map;
    }

    // 申请联系
    @PostMapping("/appliesForContact")
    public Map<String, Object> appliesForContact(@RequestBody Map<String, Object> info, HttpServletRequest request) {
        if (this.session == null) {
            this.session = request.getSession();
        }
        //HttpSession session = request.getSession();
        Map<String, Object> map = new HashMap<>();
        Reason r= reasonService.selectMyApplyBypID((String) info.get("pID"),(String) this.session.getAttribute("openid"));
        if(r!=null){
            map.put("code", 2);
            map.put("msg", "已经申请过了");
            return map;
        }

        Reason reason = new Reason();
        reason.setpID((String) info.get("pID"));
        reason.setfID((String) this.session.getAttribute("openid"));
        reason.settID((String) info.get("tID"));
        reason.setMessage((String) info.get("message"));
        reason.setState(0);
        // 提交申请，返回提交结果
        int result = reasonService.appliesForContact(reason);
        // 返回发布结果

        if (result == 1) {
            map.put("code", 1);
            map.put("msg", "发布申请成功");
        } else {
            map.put("code", 0);
            map.put("msg", "发布申请失败");
        }
        return map;
    }

    //返回“我申请的”
    @GetMapping("/myApplies")
    public Reason[] myApplies(){

        String fID = (String)session.getAttribute("openid");
        Reason[] reasons = applies(fID,false);

        return reasons;
    }

    //返回“向我申请的"
    @GetMapping("/receivedApplies")
    public Reason[] myReceivedApplies(){

        String tID = (String)session.getAttribute("openid");
        Reason[] reasons = applies(tID,true);

        return reasons;

    }

    private Reason[] applies(String ID ,Boolean tFlag){

/*     String tID = (String)session.getAttribute("openid");
     return reasonService.myReceivedApplies(tID);*/
        Reason[] reasons = null;
        if (true == tFlag){
            reasons = reasonService.myReceivedApplies(ID);
        }else {
            reasons = reasonService.myApplies(ID);
        }
        Card card = null;
        Others others = null;

        for(Reason reason : reasons){
            String pID = reason.getpID();
            card = cardService.findCardBystuID(pID);
            try{
                others = othersService.selectObjectByoID(Integer.parseInt(pID));
            }catch (Exception e){
                others = null;
            }


            if(card != null){

                reason.setObject(card);
                reason.setType(0);

            }else if (others != null){

                reason.setObject(others);
                reason.setType(1);

            }else {

                reason.setObject(null);
                reason.setType(null);

            }

            Integer state = reason.getState();

            if (true == tFlag){ // 向我申请的

                if( state == 1 ){ // 申请已同意

                    //reason.setMsg(null);

                }else if (state == 2){ // 申请已拒绝

                }

            }else { // 我申请的

                if( state == 1 ){ // 申请已同意

                    String tID = reason.gettID();
                    String contact = userProfiledao.getUserProfile(tID).getContact();
                    reason.setMsg(contact);

                }else if (state == 2){ // 申请已拒绝

                }

            }


        }

        return reasons;

    }


}
