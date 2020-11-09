package com.iqoverflow.lostandfound.controller;

import com.iqoverflow.lostandfound.domain.Others;
import com.iqoverflow.lostandfound.service.OthersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
public class OthersController {

    @Autowired
    private OthersService othersService;

    // 获取所有others
    @GetMapping("/getOthersList")
    public List<Others> getOthersList(Model model) {
        List<Others> othersList = othersService.getOthersList();
        model.addAttribute("othersList", othersList);
        return othersList;
    }

    // 发布物品
    @PostMapping("/publishOthers")
    public Boolean publishOthers(@RequestBody Map<String, Object> othersMap, HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        // othersMap转为others对象
        Others others = new Others();
        others.setoID(1);
        others.setTitle((String) othersMap.get("title"));
        others.setContent((String) othersMap.get("content"));
        others.setPic((String) othersMap.get("pic"));
        others.setuID((String) httpSession.getAttribute("openid"));
        others.setFlag((Boolean) othersMap.get("flag"));
        others.setTime(new Timestamp(System.currentTimeMillis()));
        // 发布物品信息，返回发布结果成功与否
        int result = othersService.publishOthers(others);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }


}
