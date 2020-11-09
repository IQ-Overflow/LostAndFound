package com.iqoverflow.lostandfound.service.impl;

import com.iqoverflow.lostandfound.dao.ReasonDao;
import com.iqoverflow.lostandfound.domain.Reason;
import com.iqoverflow.lostandfound.service.ReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("reasonService")
public class ReasonServiceImpl implements ReasonService {
    @Autowired
    private ReasonDao reasonDao;

    //查询fID的所有申请
    @Override
    public Reason[] myApplies(String fID) {
        return reasonDao.selectApplyByfId(fID);
    }
}
