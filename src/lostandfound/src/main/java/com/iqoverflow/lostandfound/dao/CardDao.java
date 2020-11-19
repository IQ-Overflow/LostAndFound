package com.iqoverflow.lostandfound.dao;

import com.iqoverflow.lostandfound.domain.Card;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Mapper
@Repository
public interface CardDao {
    @Select("SELECT * FROM CARD")
    Card[] findCards();

    // 发布卡
    @Insert("INSERT INTO Card(stuID,college,stuName,uID,flag,time)" +
            "VALUES( #{stuID} , #{college} , #{stuName} , #{uID} , #{flag}  , #{time}  )")
    void postCard(Card card);

    // 根据ID得到微信联系方式
    @Select("SELECT wxAccount FROM user WHERE uID = #{uID}")
    String getWxByuID(String uID);

    // 根据输入的信息找卡
    @Select("SELECT * FROM Card WHERE stuID = #{stuID} AND college = #{college} AND stuName = #{stuName}")
    @Results(
            value  ={
            @Result(property = "poster",column = "uID" , one = @One(select = "com.iqoverflow.lostandfound.dao.UserProfiledao.getUserProfile")),
            @Result(property = "uID", column = "uID")
            }
    )
    Card findCardByInfo(String stuID,String college,String stuName);
}
