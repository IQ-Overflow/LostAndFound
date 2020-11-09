package com.iqoverflow.lostandfound.dao;

import com.iqoverflow.lostandfound.domain.Reason;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReasonDao {

    //查询fID的所有申请
    @Select("SELECT * FROM reason WHERE fID = #{fID}")
    Reason[] selectApplyByfId(String fID);

}
