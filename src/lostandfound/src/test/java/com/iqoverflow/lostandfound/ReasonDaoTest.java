package com.iqoverflow.lostandfound;

import com.iqoverflow.lostandfound.dao.ReasonDao;
import com.iqoverflow.lostandfound.domain.Reason;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReasonDaoTest {
    @Autowired
    ReasonDao reasonDao;

    @Test
    public void testFindAll(){
        Reason[] reasons = reasonDao.selectApplyByfId("2");
        for (Reason reason : reasons){
            System.out.println(reason);
        }
    }
}
