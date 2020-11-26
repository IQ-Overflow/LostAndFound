package com.iqoverflow.lostandfound.service;

import com.iqoverflow.lostandfound.domain.Others;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OthersService {

    // 删除已发布的物品
    int removeOthers(Others others);

    // 按分页方式展示others信息
    List<Others> getOthersForPage(int begin, int pageSize);

    // 获取所有others
    List<Others> getOthersList();

    // 发布物品
    int publishOthers(Others others);

    // 根据oID找Others
    Others selectObjectByoID(Integer oID);
}
