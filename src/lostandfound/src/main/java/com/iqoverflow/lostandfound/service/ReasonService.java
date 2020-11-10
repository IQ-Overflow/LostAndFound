package com.iqoverflow.lostandfound.service;

import com.iqoverflow.lostandfound.domain.Reason;

public interface ReasonService {

    // 申请联系
    int appliesForContact(Reason reason);

    // 查询fID的所有申请
    Reason[] myApplies(String fID);

    // 查询tID收到的申请
    Reason[] myReceivedApplies(String tID);
}
