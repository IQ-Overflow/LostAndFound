package com.iqoverflow.lostandfound.controller;

import com.iqoverflow.lostandfound.domain.Others;
import com.iqoverflow.lostandfound.service.OthersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    // 按分页方式返回others
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
        return othersService.getOthersForPage(begin, pageSize);
    }

    // 获取所有others
    @GetMapping("/getOthersList")
    public List<Others> getOthersList() {
        return othersService.getOthersList();
    }

    // 发布物品
    @PostMapping("/publishOthers")
    public Map<String, Object> publishOthers(@RequestBody Map<String, Object> info, HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        // othersMap转为others对象
        Others others = new Others();
        others.setoID(1);
        others.setTitle((String) info.get("title"));
        others.setContent((String) info.get("content"));
        others.setPic("nopic");
        // 获取用户id
        others.setuID((String) httpSession.getAttribute("openid"));
        others.setFlag((Boolean) info.get("flag"));
        others.setTime(new Timestamp(System.currentTimeMillis()));
        // 发布物品信息
        int result = othersService.publishOthers(others);
        // 返回发布结果
        Map<String, Object> map = new HashMap<>();
        if (result == 1) {
            map.put("code",1);
            map.put("msg", "发布成功");
        } else {
            map.put("code",0);
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
