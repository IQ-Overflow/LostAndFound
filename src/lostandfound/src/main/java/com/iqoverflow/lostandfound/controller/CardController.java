package com.iqoverflow.lostandfound.controller;

import com.iqoverflow.lostandfound.domain.Card;
import com.iqoverflow.lostandfound.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Map;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    //@GetMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/findCards")
    public Card[] findCards(){
        return cardService.findAllCards();
    }

    //发布学生卡
    @PostMapping("/postCard")
    public Boolean postCard(@RequestBody Map<String,Object> info, HttpServletRequest request){
        HttpSession session = request.getSession();

        String stuID = (String)info.get("stuID");
        String college = (String)info.get("college");
        String stuName = (String)info.get("stuName");
        String uID = (String) session.getAttribute("openid");
        Boolean flag = (Boolean)info.get("flag");
        Timestamp time = new Timestamp(System.currentTimeMillis());

        Card card = new Card();
        card.setStuID(stuID);
        card.setCollege(college);
        card.setuID(uID);
        card.setStuName(stuName);
        card.setFlag(flag);
        card.setTime(time);
        //System.out.println(card);

        cardService.postCard(card);
        return true;
    }

    //根据信息找卡
    @PostMapping("/searchCard")
    public Card searchcard(@RequestBody Map<String,Object> info){
        String stuID = (String)info.get("stuID");
        String college = (String)info.get("college");
        String stuName = (String)info.get("stuName");
        Card card = cardService.findCardByInfo(stuID, college, stuName);

        if(null == card){//输入的学生卡错误或不匹配

        }else {
        
        }

        return card;
    }

    //根据uID获取微信
    @GetMapping("/contact")
    public String applyForWx(@RequestParam("uID")String uID){
        return cardService.getWxByuID(uID);
    }




}
