package com.iqoverflow.lostandfound.service;

import com.iqoverflow.lostandfound.domain.Others;

import java.util.List;

public interface OthersService {

    // 获取所有others
    List<Others> getOthersList();

    // 发布物品
    int publishOthers(Others others);


}
