package com.iqoverflow.lostandfound.dao;

import com.iqoverflow.lostandfound.domain.Card;
import com.iqoverflow.lostandfound.domain.Others;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GoodsDao {
    //其他物品
    @Select("select * from others where uid=#{uid} and flag=#{flag} ORDER BY time DESC")
    @Results(
            value  ={
                    @Result(property = "poster",column = "uID" , one = @One(select = "com.iqoverflow.lostandfound.dao.UserProfiledao.getUserProfile")),
                    @Result(property = "uID", column = "uID")
            }
    )
    Others[] getOthersInformation(String uid, int flag);

    //学生卡
    @Select("select * from card where uid=#{uid} and flag=#{flag} ORDER BY time DESC")
    @Results(
            value  ={
                    @Result(property = "poster",column = "uID" , one = @One(select = "com.iqoverflow.lostandfound.dao.UserProfiledao.getUserProfile")),
                    @Result(property = "uID", column = "uID"),
                    @Result(property = "state" , column = "state")
            }
    )
    Card[] getCardInformation(String uid, int flag);


}
