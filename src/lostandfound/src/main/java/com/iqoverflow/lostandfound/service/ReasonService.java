package com.iqoverflow.lostandfound.service;

import com.iqoverflow.lostandfound.domain.Reason;

public interface ReasonService {

    // 同意申请
    int agreeApplies(Reason reason);

    // 拒绝申请
    int refuseApplies(Reason reason);

    // 申请联系
    int appliesForContact(Reason reason);

    // 查询fID的所有申请
    Reason[] myApplies(String fID);

    // 查询tID收到的申请
    Reason[] myReceivedApplies(String tID);

    // 查找“我申请”的某个物品
    Reason selectMyApplyBypID(String pID , String fID);
}
