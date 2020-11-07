package com.iqoverflow.lostandfound.controller;

import com.iqoverflow.lostandfound.domain.Card;
import com.iqoverflow.lostandfound.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Map;

@RestController
public class CardController {
    @Autowired
    ICardService cardService;
    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/findcards")
    public Card[] findCards(){
        return cardService.findAllCards();
    }

    //发布学生卡
    @PostMapping("/postcard")
    public String postCard(@RequestBody Map<String,Object> info){
        String stuID = (String)info.get("stuID");
        String college = (String)info.get("college");
        String stuName = (String)info.get("stuName");
        String uID = (String) info.get("uID");
        Boolean flag = (Boolean)info.get("flag");
        Timestamp time = Timestamp.valueOf((String)info.get("time"));

        Card card = new Card();
        card.setStuID(stuID);
        card.setCollege(college);
        card.setuID(uID);
        card.setStuName(stuName);
        card.setFlag(flag);
        card.setTime(time);
        System.out.println(card);

        cardService.postCard(card);
        return "success";
    }

    //根据信息找卡
    @PostMapping("/searchcard")
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

/*    //根据信息找卡
    @PostMapping("/searchcard2")
    public Card searchcard2( String stuID, String college,String stuName){

        Card card = cardService.findCardByInfo(stuID, college, stuName);
        if(null == card){//输入的学生卡错误或不匹配

        }else {

        }

        return card;
    }*/


}
