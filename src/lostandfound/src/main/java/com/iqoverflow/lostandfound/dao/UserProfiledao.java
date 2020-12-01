package com.iqoverflow.lostandfound.dao;

import com.iqoverflow.lostandfound.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserProfiledao {
    @Insert("insert into user (uID,userName,sex,avatarUrl,country,province,city,language) values (#{uID},#{userName},#{sex},#{avatarUrl},#{country},#{province},#{city},#{language})")
    void setUserProfile(User user);


    @Select("select * from User where uID=#{id}")
    User getUserProfile(String id);

    @Update("UPDATE user SET contact = #{contact} WHERE uID = #{uID}")
    void setUserContact(String uID,String contact);


}
