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
        if( c != null && (c.getState() == 1 || c.getState() == 2 )){
            cardService.repostCard(stuID);
            cardService.changeTypeOfCard(stuID,flag);
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

            msg = new Message(false,"该学生卡已被发布。请通过搜索学生卡，与发布人取得联系。");
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
    public  Map<String,Object> searchcard(@RequestBody Map<String,Object> info){

        //Message msg;
        Map<String,Object> resultMap = new HashMap<>();

        String stuID = (String)info.get("stuID");
        String college = (String)info.get("college");
        String stuName = (String)info.get("stuName");
        Card card = cardService.findCardByInfo(stuID, college, stuName);
        Boolean flag = false; // 是否存在该卡
        Boolean isApplied = false; // 用户是否申请过联系
        Boolean isDeleted = false; // 卡是否已删除


        if(null == card){// 输入的学生卡错误或不匹配或已删除

            //msg = new Message(false,card);
            flag = false;
            isApplied = false;


        }else {

            String fID = (String) this.session.getAttribute("openid");
            //String fID = (String)info.get("fID");
            String pID = card.getStuID();
            Reason reason = reasonService.selectMyApplyBypID(pID, fID);
            flag = true;
            System.out.println("fID:" + fID + "   uID:" + card.getuID());
            isDeleted = card.getState() == 1?true:false;
            // reason 为 null 则表示用户还没申请联系
            if (card.getuID().equals(fID)){
                isApplied = false;
                System.out.println("本人查找，可返回卡。");
            }
            else if (null != reason   ){ // 已申请
                isApplied = true;
                System.out.println("用户早已申请了卡，可以返回卡");

            }else { // 未申请
                isApplied = false;

                if(card.getState() == 1 || card.getState() == 2){ // 卡已结束或删除
                    isDeleted = true;
                    card = null;
                    System.out.println("卡已结束或删除了" );
                }else {
                    System.out.println("卡还在");
                }
            }


           //msg = new Message(true,card);
        }

        resultMap.put("flag",flag);
        resultMap.put("isDeleted",isDeleted);
        resultMap.put("isApplied",isApplied);
        resultMap.put("msg",card);

        //return msg;
        return resultMap;
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
        //Boolean flag = (Boolean)info.get("flag");

        try{
            cardService.cancelCard(stuID);
        }catch (Exception e){
            msg = new Message(false,"撤销失败,不存在该贴子");
            return msg;
        }

        msg = new Message(true,"撤销成功");
        return msg;
    }

    @DeleteMapping("delete")
    public Message deleteCard(@RequestBody Map<String,Object> info){
        Message msg;
        String stuID = (String)info.get("stuID");
        //Boolean flag = (Boolean)info.get("flag");

        try{
            cardService.deleteCard(stuID);
        }catch (Exception e){
            msg = new Message(false,"删除失败,不存在该贴子");
            return msg;
        }

        msg = new Message(true,"删除成功");
        return msg;
    }




}
