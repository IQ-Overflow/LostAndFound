package com.iqoverflow.lostandfound.dao;

import com.iqoverflow.lostandfound.domain.Card;
import com.iqoverflow.lostandfound.domain.Others;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GoodsDao {
    //其他物品
    @Select("select * from others where uid=#{uid} and flag=#{flag}")
    Others[] getOthersInformation(String uid, int flag);

    //学生卡
    @Select("select * from card where uid=#{uid} and flag=#{flag}")
    Card[] getCardInformation(String uid, int flag);


}
