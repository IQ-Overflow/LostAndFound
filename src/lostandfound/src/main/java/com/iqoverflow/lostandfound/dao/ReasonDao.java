package com.iqoverflow.lostandfound.dao;

import com.iqoverflow.lostandfound.domain.Reason;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReasonDao {

    // 修改reason的state属性
    @Update("update reason set state = #{state} where pID = #{pID} and fID = #{fID}")
    int updateState(Reason reason);

    // 插入reason
    @Insert("INSERT INTO reason(`pID`,`fID`,`tID`,`message`,`state`) VALUES( #{pID}, #{fID}, #{tID}, #{message}, #{state} )")
    int insertReason(Reason reason);

    //查询fID的所有申请
    @Select("SELECT * FROM reason WHERE fID = #{fID}")
    Reason[] selectApplyByfId(String fID);

}
