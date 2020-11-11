package com.iqoverflow.lostandfound.controller;


import com.iqoverflow.lostandfound.service.GoodsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    //我捡到的物品
    @GetMapping("/whatIFound")
    public List whatIFound(HttpSession session) {
        String openid = (String) session.getAttribute("openid");
        List goodsInformation = goodsService.getGoodsInformation(openid, 1);
        return goodsInformation;


    }

    //我丢失的物品
    @GetMapping("/myLostItems")
    public List myLostItems(HttpSession session) {
        String openid = (String) session.getAttribute("openid");
        List goodsInformation = goodsService.getGoodsInformation(openid, 0);

        return null;
    }


}
