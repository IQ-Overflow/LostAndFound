package com.iqoverflow.lostandfound;

import com.iqoverflow.lostandfound.dao.OthersDao;
import com.iqoverflow.lostandfound.domain.Others;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@SpringBootTest
class LostandfoundApplicationTests {

    // 对ortherDao做了些测试
    @Autowired
    private OthersDao othersDao;

    @Test
    void contextLoads() {
        List<Others> othersList = othersDao.selectOthersList();
        for (Others others : othersList) {
            System.out.println(others);
        }
    }

    @Test
    void insertOthersTest() {
        Others others = new Others();
        others.setoID(1);
        others.setTitle("不见了一只猪");
        others.setContent("昨天晚上在工一415");
        others.setPic("");
        others.setuID("2");
        others.setFlag(false);
        others.setTime(new Timestamp(new Date().getTime()));
        int i = othersDao.insertOthers(others);
        System.out.println("影响的行数为：" + i);
    }

}
