package com.iqoverflow.lostandfound.service.impl;

import com.iqoverflow.lostandfound.dao.ReasonDao;
import com.iqoverflow.lostandfound.domain.Card;
import com.iqoverflow.lostandfound.domain.Others;
import com.iqoverflow.lostandfound.domain.Reason;
import com.iqoverflow.lostandfound.service.ReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("reasonService")
public class ReasonServiceImpl implements ReasonService {
    @Autowired
    private ReasonDao reasonDao;

    // 同意申请
    @Override
    public int agreeApplies(Reason reason) {
        return reasonDao.updateState(reason);
    }

    // 拒绝申请
    @Override
    public int refuseApplies(Reason reason) {
        return reasonDao.updateState(reason);
    }

    // 申请联系
    @Override
    public int appliesForContact(Reason reason) {
        return reasonDao.insertReason(reason);
    }

    // 查询fID的所有申请
    @Override
    public Reason[] myApplies(String fID) {
        return reasonDao.selectApplyByfId(fID);
    }

    // 查询tID收到的申请
    @Override
    public Reason[] myReceivedApplies(String tID) {
        return  reasonDao.selectReceivedAppliesOftID(tID);
    }


    @Override
    public Reason selectMyApplyBypID(String pID, String fID) {
        return reasonDao.selectMyApplyBypID(pID,fID);
    }




}
