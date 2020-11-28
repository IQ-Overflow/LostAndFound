package com.iqoverflow.lostandfound.controller;

import com.iqoverflow.lostandfound.domain.Others;
import com.iqoverflow.lostandfound.interceptor.AdminInterceptor;
import com.iqoverflow.lostandfound.service.OthersService;
import com.iqoverflow.lostandfound.service.UserProfileservice;
import com.iqoverflow.lostandfound.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
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
    public ModelAndView index(HttpServletRequest request) {
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

    // 按分页方式返回未删除的others（无图片）
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

    // 按分页方式返回未删除的others（有图片）
    @PostMapping("/getOthersForPageWithPic")
    public List<Others> getOthersForPageWithPic(@RequestBody Map<String, Object> info) throws IOException {
        // 调用（上面无图片返回的方法）
        List<Others> othersList = getOthersForPage(info);
        // 将图片一起返回
        for (Others others : othersList) {
            String pic = others.getPic();
            if ("nopic".equals(pic)) {
                others.setImgStr("nopic");
            } else {
                String imageStr = ImageUtils.readImageByBase64(pic);
                others.setImgStr(imageStr);
            }
        }
        return othersList;
    }

    // 获取所有others（无图片）
    @GetMapping("/getOthersList")
    public List<Others> getOthersList() {
        return othersService.getOthersList();
    }

    // 获取所有others（有图片）
    @GetMapping("/getOthersListWithPic")
    public List<Others> getOthersListWithPic() throws IOException {
        List<Others> othersList = othersService.getOthersList();
        // 将图片一起返回
        for (Others others : othersList) {
            String pic = others.getPic();
            if ("nopic".equals(pic)) {
                others.setImgStr("nopic");
            } else {
                String imageStr = ImageUtils.readImageByBase64(pic);
                others.setImgStr(imageStr);
            }
        }
        return othersList;
    }

    // 发布物品（无图片）
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

    // 发布物品（有图片）
    @PostMapping("/publishOthersWithPic")
    public Map<String, Object> publishOthersWithPic(Others othersFromWeb, HttpServletRequest request) {
        if (this.session == null) {
            this.session = request.getSession();
        }
        // 返回结果
        Map<String, Object> map = new HashMap<>();
        // 进行图片的处理
        MultipartFile imgFile = othersFromWeb.getImgFile();
        if (imgFile == null) {
            map.put("code", 0);
            map.put("msg", "没有上传图片");
            return map;
        }
        // 获取后缀
        String suffixName = ImageUtils.getImagePath(imgFile);
        // 获取新的文件名
        String newFileName = ImageUtils.getNewFileName(suffixName);
        // 保存图片
        File file = new File(ImageUtils.getNewImagePath(newFileName));
        boolean state = ImageUtils.saveImage(imgFile, file);
        if (state) {
            //图片保存成功
            //设置图片到对象
            String imagePath = ImageUtils.getNewImagePath(newFileName);
            Others others = new Others();
            others.setPic(imagePath);
            others.setoID(1);//无用，数据库的oid为自增
            others.setTitle(othersFromWeb.getTitle());
            others.setContent(othersFromWeb.getContent());
            others.setFlag(othersFromWeb.isFlag());
            others.setTime(new Timestamp(System.currentTimeMillis()));
            others.setState(0);
            // 获取用户id
            String uid = (String) this.session.getAttribute("openid");
            others.setuID(uid);
//            String uid = "3";
//            others.setuID(uid);
            // 保存contact
            String contact = othersFromWeb.getContact();
            userProfileservice.setUserContact(uid, contact);
            // 保存others
            int result = othersService.publishOthers(others);
            if (result == 1) {
                map.put("code", 1);
                map.put("msg", "发布成功");
            } else {
                map.put("code", 0);
                map.put("msg", "发布失败");
            }
        } else {
            map.put("code", 0);
            map.put("msg", "图片上传失败");
        }
        return map;
    }


    @RequestMapping("/test")
    public Map<String, Object> test() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("msg", "发布成功");
        return map;
    }


}
