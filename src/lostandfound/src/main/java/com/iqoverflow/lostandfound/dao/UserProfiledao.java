package com.iqoverflow.lostandfound.dao;

import com.iqoverflow.lostandfound.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserProfiledao {
    @Insert("insert into user (uID,userName,sex,avatarUrl,country,province,city,language) values (#{uID},#{userName},#{sex},#{avatarUrl},#{country},#{province},#{city},#{language})")
    static void setUserProfile(User user) {
    }


    @Select("select * from User where uID=#{id}")
    abstract User getUserProfile(String id);


}
