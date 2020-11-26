package com.iqoverflow.lostandfound.dao;

import com.iqoverflow.lostandfound.domain.Others;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OthersDao {

    // 按分页方式查询others
    @Select("select oID, title, content, pic, uID, flag, time, state from others limit #{begin}, #{pageSize}")
    @Results(
            value = {
                    @Result(property = "poster", column = "uID", one = @One(select = "com.iqoverflow.lostandfound.dao.UserProfiledao.getUserProfile")),
                    @Result(property = "uID", column = "uID"),
                    @Result(property = "state", column = "state")
            }
    )
    List<Others> selectOthersForPage(int begin, int pageSize);

    // 查询所有others
    @Select("select * from others")
    @Results(
            value = {
                    @Result(property = "poster", column = "uID", one = @One(select = "com.iqoverflow.lostandfound.dao.UserProfiledao.getUserProfile")),
                    @Result(property = "uID", column = "uID"),
                    @Result(property = "state", column = "state")
            }
    )
    List<Others> selectOthersList();

    // 插入others
    @Insert("INSERT INTO `others`\n" +
            "(`title`,`content`,`pic`,`uID`,`flag`,`time`) \n" +
            "VALUES \n" +
            "( #{title}, #{content}, #{pic}, #{uID}, #{flag}, #{time} )")
    int insertOthers(Others others);


    // 根据oID找Others
    @Select("SELECT * FROM others WHERE oID = #{oID}")
    Others selectObjectByoID(Integer oID);

}
