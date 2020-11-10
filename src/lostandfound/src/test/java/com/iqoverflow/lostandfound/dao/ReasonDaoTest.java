package com.iqoverflow.lostandfound.dao;

import com.iqoverflow.lostandfound.domain.Reason;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReasonDaoTest {

    @Autowired
    private ReasonDao reasonDao;

    @Test
    public void updateStateTest() {
        Reason reason = new Reason();
        reason.setpID("2");
        reason.setfID("2");
        reason.setState(1);

        reason.settID("0");
        reason.setMessage("0");
        int i = reasonDao.updateState(reason);
        System.out.println("===========" + i + "===========");
    }

    @Test
    public void insertReasonTest() {
        Reason reason = new Reason();
        reason.setpID("2");
        reason.setfID("2");
        reason.settID("3");
        reason.setMessage("是我的东西，在四饭丢了的，123456饭酬谢！");
        reason.setState(0);
        int i = reasonDao.insertReason(reason);
        System.out.println("===========" + i + "===========");
    }

}
