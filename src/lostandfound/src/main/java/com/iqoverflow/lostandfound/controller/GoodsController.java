package com.iqoverflow.lostandfound.controller;


import com.iqoverflow.lostandfound.interceptor.AdminInterceptor;
import com.iqoverflow.lostandfound.service.GoodsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    private HttpSession session = null;
    @ModelAttribute
    public ModelAndView index(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();

        session = AdminInterceptor.session;

        return modelAndView;
    }

    //我捡到的物品
    @GetMapping("/whatIFound")
    public List whatIFound( HttpServletRequest request) {
        if (this.session == null) {
            this.session = request.getSession();
        }
        String openid = (String) session.getAttribute("openid");
        List goodsInformation = goodsService.getGoodsInformation(openid, 1);
        return goodsInformation;


    }

    //我丢失的物品
    @GetMapping("/myLostItems")
    public List myLostItems(HttpServletRequest request) {
        if (this.session == null) {
            this.session = request.getSession();
        }
        String openid = (String) session.getAttribute("openid");
        List goodsInformation = goodsService.getGoodsInformation(openid, 0);

        return goodsInformation;
    }


}
