package com.iqoverflow.lostandfound.service;

import com.iqoverflow.lostandfound.domain.Card;

import java.sql.Timestamp;

public interface CardService {

    // 返回所有学生卡
    Card[] findAllCards();

    // 发布卡
    void postCard(Card card);

    // 根据信息找卡
    Card findCardByInfo(String stuID,String college,String stuName);

    // 根据uID获取微信联系方式
    String getWxByuID(String uID);

    //撤销寻卡贴
    void cancelCard(String stuID,Boolean flag);

    //恢复寻卡贴
    void repostCard(String stuID,Boolean flag);
}
