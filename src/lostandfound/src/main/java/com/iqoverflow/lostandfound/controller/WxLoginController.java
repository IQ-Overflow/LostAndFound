package com.iqoverflow.lostandfound.controller;

import com.iqoverflow.lostandfound.domain.State;
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
    public State wxLogin(@RequestParam(value = "code", required = false) String code, @RequestBody Map<String,Object> map, HttpSession session){
        if(code==null){
            State state =new State();
            state.setCode("500");
            state.setMsg("登录失败");

            return state;
        }

        String url= "https://api.weixin.qq.com//sns/jscode2session";
        Map<String,String> param=new HashMap<>();
        param.put("appid","wx52e69bcad670ab80");
        param.put("secret","20bb8c64ccf43aa970f9fabe1a0ccbf5");
        param.put("js_code",code);
        param.put("grant_type","authorization_code");
        //获得session_key和openid
       String wxResult = HttpClientUtil.doGet(url, param);
        System.out.println("RESULT"+wxResult);
        //将session_key和openid封装到一个对象
        WXSessionModel wxSessionModel = JsonUtils.jsonToPojo(wxResult, WXSessionModel.class);
        System.out.println(wxSessionModel.toString());
        System.out.println(wxSessionModel.getOpenid());
        //根据openid到数据库查询有没有这个用户
        User user = userProfileservice.getUserProfile(wxSessionModel.getOpenid());
        System.out.println("user"+user);
        //，如果数据库没有openID，则是第一次登录
        if(user==null){
               user = new User();
               user.setuID(wxSessionModel.getOpenid());
               System.out.println("用户的openid ： " + wxSessionModel.getOpenid());
               user.setUserName((String) map.get("nickName"));
               user.setSex((Integer) map.get("gender"));
               user.setAvatarUrl((String) map.get("avatarUrl"));
               user.setCity((String) map.get("city"));
               user.setProvince((String) map.get("province"));
               user.setCountry((String) map.get("country"));
               user.setLanguage((String) map.get("language"));
               userProfileservice.setUserProfile(user);

        }

          session.setAttribute("user",user);
        session.setAttribute("openid",user.getuID());
        State state =new State();
        state.setCode("200");
        state.setMsg("登录成功");
        state.setOpenid(wxSessionModel.getOpenid());


        return state;

    }


}
