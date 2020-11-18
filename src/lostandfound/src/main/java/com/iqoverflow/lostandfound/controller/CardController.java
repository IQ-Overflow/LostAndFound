package com.iqoverflow.lostandfound.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iqoverflow.lostandfound.domain.Card;
import com.iqoverflow.lostandfound.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.HashMap;
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
    public String postCard(@RequestBody Map<String,Object> info, HttpServletRequest request) throws JsonProcessingException {
        HttpSession session = request.getSession();
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> resultMap = new HashMap();


        String stuID = (String)info.get("stuID");
        String college = (String)info.get("college");
        String stuName = (String)info.get("stuName");
        String uID = (String) session.getAttribute("openid");
        Boolean flag = (Boolean)info.get("flag");
        Timestamp time = new Timestamp(System.currentTimeMillis());

        if(stuID == null || college == null || stuName == null || flag == null){
            resultMap.put("msg","请输入完整的信息！");
            resultMap.put("flag",false);
            return mapper.writeValueAsString(resultMap);
        }

        if(stuID.length() != 10){
            resultMap.put("msg","请输入正确的学号！");
            resultMap.put("flag",false);
            return mapper.writeValueAsString(resultMap);
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
            resultMap.put("flag",false);
            resultMap.put("msg","该学生卡已被发布！");

            return mapper.writeValueAsString(resultMap);
        }
        resultMap.put("flag",true);
        resultMap.put("msg","发布成功");

        return mapper.writeValueAsString(resultMap);
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
