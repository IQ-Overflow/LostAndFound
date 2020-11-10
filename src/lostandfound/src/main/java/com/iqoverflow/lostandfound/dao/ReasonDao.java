package com.iqoverflow.lostandfound.dao;

import com.iqoverflow.lostandfound.domain.Reason;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReasonDao {

    // 插入reason
    @Insert("INSERT INTO reason(`pID`,`fID`,`tID`,`message`,`state`) VALUES( #{pID}, #{fID}, #{tID}, #{message}, #{state} )")
    int insertReason(Reason reason);

    // 查询fID的所有申请
    @Select("SELECT * FROM reason WHERE fID = #{fID}")
    Reason[] selectApplyByfId(String fID);

    // 查询fID收到的申请
    @Select("SELECT * FROM reason WHERE tID = #{tID}")
    Reason[] selectReceivedAppliesOftID(String tID);

}
