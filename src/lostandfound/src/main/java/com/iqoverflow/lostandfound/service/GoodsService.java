package com.iqoverflow.lostandfound.service;

import java.util.List;

public interface GoodsService {
    //通过flag判断是获得我捡到的物品信息还是我丢失物品的信息
    List getGoodsInformation(String uid, int flag);

}
