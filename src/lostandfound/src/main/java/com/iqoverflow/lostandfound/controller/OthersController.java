package com.iqoverflow.lostandfound.controller;

import com.iqoverflow.lostandfound.domain.Others;
import com.iqoverflow.lostandfound.service.OthersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public String publishOthers(@RequestBody Map<String,Object> othersMap) {
        Others others = new Others();
        others.setoID(1);
        others.setTitle((String) othersMap.get("title"));
        others.setContent((String) othersMap.get("content"));
        others.setPic((String) othersMap.get("pic"));
        others.setuID((String) othersMap.get("uID"));
        others.setFlag((Boolean) othersMap.get("flag"));
        others.setTime((Timestamp) othersMap.get("time"));
        int result = othersService.publishOthers(others);
        if (result == 1) {
            return "发布成功";
        }else {
            return "发布失败";
        }
    }


}
