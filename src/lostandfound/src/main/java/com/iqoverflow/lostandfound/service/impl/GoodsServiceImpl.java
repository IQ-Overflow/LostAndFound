package com.iqoverflow.lostandfound.service.impl;

import com.iqoverflow.lostandfound.dao.GoodsDao;
import com.iqoverflow.lostandfound.domain.Card;
import com.iqoverflow.lostandfound.domain.Others;
import com.iqoverflow.lostandfound.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public List getGoodsInformation(String uid, int flag) {
        //其他物品中获得信息
        Others[] othersInformation = goodsDao.getOthersInformation(uid, flag);
        //学生卡中获得信息
        Card[] cardInformation = goodsDao.getCardInformation(uid, flag);
        List list = new ArrayList<>();
        list.add(othersInformation);
        list.add(cardInformation);
        return list;

    }
}
