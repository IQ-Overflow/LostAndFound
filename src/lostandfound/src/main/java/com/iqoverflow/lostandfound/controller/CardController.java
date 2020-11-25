package com.iqoverflow.lostandfound.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iqoverflow.lostandfound.domain.Card;
import com.iqoverflow.lostandfound.domain.Message;
import com.iqoverflow.lostandfound.domain.Reason;
import com.iqoverflow.lostandfound.domain.User;
import com.iqoverflow.lostandfound.interceptor.AdminInterceptor;
import com.iqoverflow.lostandfound.listener.MySessionContext;
import com.iqoverflow.lostandfound.service.CardService;
import com.iqoverflow.lostandfound.service.ReasonService;
import com.iqoverflow.lostandfound.service.UserProfileservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    @Autowired
    UserProfileservice userProfileservice;

    @Autowired
    ReasonService reasonService;

    private HttpSession session = null;

    @ModelAttribute
    public ModelAndView index(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();

        session = AdminInterceptor.session;

        return modelAndView;
    }

    @GetMapping("/findCards")
    public Card[] findCards(){
        return cardService.findAllCards();
    }

    //发布学生卡
    @PostMapping("/postCard")
    public Message postCard(@RequestBody Map<String,Object> info, HttpServletRequest request) throws JsonProcessingException {

        Message msg ;

        if(this.session == null){
            this.session = request.getSession();
        }


        String stuID = (String)info.get("stuID");
        String college = (String)info.get("college");
        String stuName = (String)info.get("stuName");
        String uID = (String) this.session.getAttribute("openid");
        Boolean flag = (Boolean)info.get("flag");
        Timestamp time = new Timestamp(new Date().getTime());

        String contact =(String)info.get("contact");

        if(stuID == null || college == null || stuName == null || flag == null){
            msg = new Message(false,"请输入完整的信息");
            return msg;
        }

        if(stuID.length() != 10){
            msg = new Message(false,"请输入正确的学号");
            return msg;
        }

        if(contact == null){
            msg = new Message(false,"请输入您的联系方式");
            return msg;
        }

        //若该卡曾被发布
        Card c = cardService.findCardByInfo(stuID,college,stuName);
        if( c!=null && c.getState() == 1  ){
            cardService.repostCard(stuID,flag);
            msg = new Message(true,"发布成功");
            return msg;
        }


        Card card = new Card();
        card.setStuID(stuID);
        card.setCollege(college);
        card.setuID(uID);
        card.setStuName(stuName);
        card.setFlag(flag);
        card.setTime(time);
        //System.out.println(card);
        try{
            cardService.postCard(card);
        }catch (Exception e){

            msg = new Message(false,"该学生卡已被发布");
            return msg;
        }

        try{
            userProfileservice.setUserContact(uID,contact);
        }catch (Exception e){
            
        }


        msg = new Message(true,"发布成功");
        return msg;
    }

    //根据信息找卡
    @PostMapping("/searchCard")
    public Message searchcard(@RequestBody Map<String,Object> info){

        Message msg;

        String stuID = (String)info.get("stuID");
        String college = (String)info.get("college");
        String stuName = (String)info.get("stuName");
        Card card = cardService.findCardByInfo(stuID, college, stuName);



        if(null == card){// 输入的学生卡错误或不匹配或已删除

            msg = new Message(false,card);

        }else {

            String fID = (String) this.session.getAttribute("openid");
            String pID = card.getStuID();
            Reason reason = reasonService.selectMyApplyBypID(pID, fID);

            // reason 为 null 则表示用户还没申请联系


            msg = new Message(true,card);
        }

        return msg;
    }

    //根据uID获取微信
    @GetMapping("/contact")
    public String applyForWx(@RequestParam("uID")String uID){
        return cardService.getWxByuID(uID);
    }

    @DeleteMapping("cancel")
    public Message cancelCard(@RequestBody Map<String,Object> info){
        Message msg;
        String stuID = (String)info.get("stuID");
        Boolean flag = (Boolean)info.get("flag");

        try{
            cardService.cancelCard(stuID,flag);
        }catch (Exception e){
            msg = new Message(false,"删除失败,不存在该贴子");
        }

        msg = new Message(true,"删除成功");
        return msg;
    }




}
