package com.iqoverflow.lostandfound.dao;

import com.iqoverflow.lostandfound.domain.Others;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OthersDao {

    // 按分页方式查询others
    @Select("select oID, title, content, pic, uID, flag, time from others limit #{begin}, #{pageSize}")
    List<Others> selectOthersForPage(int begin, int pageSize);

    // 查询所有others
    @Select("select * from others")
    List<Others> selectOthersList();

    // 插入others
    @Insert("INSERT INTO `others`\n" +
            "(`title`,`content`,`pic`,`uID`,`flag`,`time`) \n" +
            "VALUES \n" +
            "( #{title}, #{content}, #{pic}, #{uID}, #{flag}, #{time} )")
    int insertOthers(Others others);


}
