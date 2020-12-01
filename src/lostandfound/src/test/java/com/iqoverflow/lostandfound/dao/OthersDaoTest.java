package com.iqoverflow.lostandfound.dao;


import com.iqoverflow.lostandfound.domain.Others;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class OthersDaoTest {

    @Autowired
    private OthersDao othersDao;

    @Test
    public void selectOthersForPageTest() {
        List<Others> othersList = othersDao.selectOthersForPage(1, 3);
        for (Others others : othersList) {
            System.out.println(others);
        }
    }

    @Test
    public void selectOthersListTest() {
        List<Others> othersList = othersDao.selectOthersList();
        for (Others others : othersList) {
            System.out.println(others);
        }
    }

    @Test
    public void insertOthersTest() {
        Others others = new Others();
        others.setoID(1);
        others.setTitle("不见了一只猪头");
        others.setContent("昨天晚上西三");
        others.setPic("");
        others.setuID("2");
        others.setFlag(false);
        others.setTime(new Timestamp(new Date().getTime()));
        int i = othersDao.insertOthers(others);
        System.out.println("影响的行数为：" + i);
    }

    @Test
    public void testFindByID(){
        Others objcet = othersDao.selectObjectByoID(1);
        System.out.println(objcet.getPoster());
    }

}
