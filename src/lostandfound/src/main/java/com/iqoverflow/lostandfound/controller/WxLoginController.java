package com.iqoverflow.lostandfound.controller;

import com.iqoverflow.lostandfound.domain.User;
import com.iqoverflow.lostandfound.domain.WXSessionModel;
import com.iqoverflow.lostandfound.service.UserProfileservice;
import com.iqoverflow.lostandfound.utils.HttpClientUtil;
import com.iqoverflow.lostandfound.utils.JsonUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WxLoginController {

    @Autowired
    private UserProfileservice userProfileservice;

    @PostMapping("/wxLogin")
    public void wxLogin(@RequestParam("code") String code, @RequestBody Map<String,Object> map, HttpSession session){
        String url= "https://api.weixin.qq.com//sns/jscode2session";
        Map<String,String> param=new HashMap<>();
        param.put("appid","wx02720ae011e85345");
        param.put("secret","b72c00a17f05a0cff0abd5f9e3ec2e75");
        param.put("js_code",code);
        param.put("grant_type","authorization_code");
        //获得session_key和openid
        String wxResult = HttpClientUtil.doGet(url, param);
        //将session_key和openid封装到一个对象
        WXSessionModel wxSessionModel = JsonUtils.jsonToPojo(wxResult, WXSessionModel.class);
        //根据openid到数据库查询有没有这个用户
        User user = userProfileservice.getUserProfile(wxSessionModel.getOpenid());
        if(user==null){
               user.setuID(wxSessionModel.getOpenid());
               user.setUserName((String) map.get("nickName"));
               user.setSex((Integer) map.get("gender"));
                userProfileservice.setUserProfile(user);

        }
          session.setAttribute("user",user);
        session.setAttribute("openid",user.getuID());

    }
}
