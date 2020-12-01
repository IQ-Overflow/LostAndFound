package com.iqoverflow.lostandfound.dao;

import com.iqoverflow.lostandfound.domain.Reason;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReasonDao {

    // 修改reason的state属性
    @Update("update reason set state = #{state} where pID = #{pID} and fID = #{fID} and tID = #{tID}")
    int updateState(Reason reason);

    // 插入reason
    @Insert("INSERT INTO reason(`pID`,`fID`,`tID`,`message`,`state`) " +
            "VALUES( #{pID}, #{fID}, #{tID}, #{message}, #{state} )")
    int insertReason(Reason reason);

    // 查询fID的所有申请
    @Select("SELECT * FROM reason WHERE fID = #{fID}")
    @Results(
            id = "reasonMap",
            value  ={
                    @Result(property = "reasonPoster",column = "fID" , one = @One(select = "com.iqoverflow.lostandfound.dao.UserProfiledao.getUserProfile")),
                    @Result(property = "fID", column = "fID")
            }
    )
    Reason[] selectApplyByfId(String fID);

    // 查询tID收到的申请
    @Select("SELECT * FROM reason WHERE tID = #{tID}")
    @ResultMap("reasonMap")
    Reason[] selectReceivedAppliesOftID(String tID);

    // 查找“我申请”的某个物品
    @Select("SELECT * FROM reason WHERE pID = #{pID} AND fID = #{fID} ")
    @ResultMap("reasonMap")
    Reason selectMyApplyBypID(String pID , String fID);



}
