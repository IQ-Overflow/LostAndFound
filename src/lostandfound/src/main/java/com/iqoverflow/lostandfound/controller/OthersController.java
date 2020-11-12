package com.iqoverflow.lostandfound.controller;

import com.iqoverflow.lostandfound.domain.Others;
import com.iqoverflow.lostandfound.service.OthersService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
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

    // 按分页方式返回others
    @GetMapping("/getOthersForPage")
    public List<Others> getOthersForPage(@Param("begin") int begin, @Param("pageSize") int pageSize) {
        return othersService.getOthersForPage(begin, pageSize);
    }

    // 获取所有others
    @GetMapping("/getOthersList")
    public List<Others> getOthersList() {
        return othersService.getOthersList();
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
        // 获取用户id
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
