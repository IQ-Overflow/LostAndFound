package com.iqoverflow.lostandfound.service;

import com.iqoverflow.lostandfound.domain.Reason;

public interface ReasonService {

    //查询fID的所有申请
    Reason[] myApplies(String fID);
}