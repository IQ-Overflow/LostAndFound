package com.iqoverflow.lostandfound.controller;

import com.iqoverflow.lostandfound.domain.Others;
import com.iqoverflow.lostandfound.interceptor.AdminInterceptor;
import com.iqoverflow.lostandfound.service.OthersService;
import com.iqoverflow.lostandfound.service.UserProfileservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/others")
public class OthersController {

    @Autowired
    private OthersService othersService;

    @Autowired
    private UserProfileservice userProfileservice;

    private HttpSession session = null;

    @ModelAttribute
    public ModelAndView index(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();

        session = AdminInterceptor.session;

        return modelAndView;
    }

    // 删除已发布的物品
    @PostMapping("/removeOthers")
    public Map<String, Object> removeOthers(@RequestBody Map<String, Object> info, HttpServletRequest request) {
        if (this.session == null) {
            this.session = request.getSession();
        }
        Others others = new Others();
        others.setoID((Integer) info.get("oID"));
        others.setuID((String) this.session.getAttribute("openid"));
        others.setState(2);
        int result = othersService.removeOthers(others);
        // 返回删除的结果
        Map<String, Object> map = new HashMap<>();
        if (result == 1) {
            map.put("code", 1);
            map.put("msg", "删除成功");
        } else {
            map.put("code", 0);
            map.put("msg", "删除失败");
        }
        return map;
    }

    // 按分页方式返回未删除的others
    @PostMapping("/getOthersForPage")
    public List<Others> getOthersForPage(@RequestBody Map<String, Object> info) {
        int pageNext = (int) info.get("pageNext");
        int pageSize = (int) info.get("pageSize");
        if (pageNext < 1) {
            pageNext = 1;
        }
        if (pageSize < 1) {
            pageSize = 1;
        }
        int begin = (pageNext - 1) * pageSize;
        List<Others> othersList = othersService.getOthersForPage(begin, pageSize);
        // 返回结果
        return othersList;
    }

    // 获取所有others
    @GetMapping("/getOthersList")
    public List<Others> getOthersList() {
        return othersService.getOthersList();
    }

    // 发布物品
    @PostMapping("/publishOthers")
    public Map<String, Object> publishOthers(@RequestBody Map<String, Object> info, HttpServletRequest request) {
        if (this.session == null) {
            this.session = request.getSession();
        }
        //HttpSession httpSession = request.getSession();
        // 获取前端数据
        Others others = new Others();
        others.setoID(1);
        others.setTitle((String) info.get("title"));
        others.setContent((String) info.get("content"));
        others.setPic("nopic");
        // 获取用户id
        String uid = (String) this.session.getAttribute("openid");
        String contact = (String) info.get("contact");
        others.setuID(uid);
        others.setFlag((Boolean) info.get("flag"));
        others.setTime(new Timestamp(System.currentTimeMillis()));
        others.setState(0);
        // 发布物品信息
        userProfileservice.setUserContact(uid, contact);
        int result = othersService.publishOthers(others);
        // 返回发布结果
        Map<String, Object> map = new HashMap<>();
        if (result == 1) {
            map.put("code", 1);
            map.put("msg", "发布成功");
        } else {
            map.put("code", 0);
            map.put("msg", "发布失败");
        }
        return map;
    }

    @RequestMapping("/test")
    public Map<String, Object> test() {
        Map<String, Object> map = new HashMap<>();
        map.put("code",1);
        map.put("msg", "发布成功");
        return map;
    }


}
