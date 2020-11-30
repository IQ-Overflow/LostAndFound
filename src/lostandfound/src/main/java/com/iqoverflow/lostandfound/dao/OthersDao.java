package com.iqoverflow.lostandfound.dao;

import com.iqoverflow.lostandfound.domain.Others;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OthersDao {

    // 修改物品的state属性
    @Update("UPDATE others SET state = #{state} WHERE oID = #{oID} AND uID = #{uID}")
    int updateOthersState(Others others);

    // 按分页方式查询未删除的others
    @Select("SELECT oID, title, content, pic, uID, flag, time, state " +
            "FROM others WHERE state IN (0,1) ORDER BY time DESC LIMIT #{begin}, #{pageSize}")
    @Results(
            value = {
                    @Result(property = "poster", column = "uID",
                            one = @One(select = "com.iqoverflow.lostandfound.dao.UserProfiledao.getUserProfile")),
                    @Result(property = "uID", column = "uID"),
                    @Result(property = "state", column = "state")
            }
    )
    List<Others> selectOthersForPage(int begin, int pageSize);

    // 查询所有others
    @Select("select * from others ORDER BY time DESC")
    @Results(
            value = {
                    @Result(property = "poster", column = "uID",
                            one = @One(select = "com.iqoverflow.lostandfound.dao.UserProfiledao.getUserProfile")),
                    @Result(property = "uID", column = "uID"),
                    @Result(property = "state", column = "state")
            }
    )
    List<Others> selectOthersList();

    // 插入others
    @Insert("INSERT INTO `others`\n" +
            "(`title`,`content`,`pic`,`uID`,`flag`,`time`,`state`) \n" +
            "VALUES \n" +
            "( #{title}, #{content}, #{pic}, #{uID}, #{flag}, #{time}, #{state} )")
    int insertOthers(Others others);


    // 根据oID找Others
    @Select("SELECT * FROM others WHERE oID = #{oID}")
    @Results(
            value = {
                    @Result(property = "poster", column = "uID",
                            one = @One(select = "com.iqoverflow.lostandfound.dao.UserProfiledao.getUserProfile")),
                    @Result(property = "uID", column = "uID"),
                    @Result(property = "state", column = "state")
            }
    )
    Others selectObjectByoID(Integer oID);

}
