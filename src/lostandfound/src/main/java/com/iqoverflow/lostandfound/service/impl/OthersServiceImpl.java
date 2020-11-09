package com.iqoverflow.lostandfound.service.impl;


import com.iqoverflow.lostandfound.dao.OthersDao;
import com.iqoverflow.lostandfound.domain.Others;
import com.iqoverflow.lostandfound.service.OthersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OthersServiceImpl implements OthersService {

    @Autowired
    private OthersDao othersDao;

    @Override
    public List<Others> getOthersForPage(int begin, int pageSize) {
        return othersDao.selectOthersForPage(begin, pageSize);
    }

    // 获取所有others
    @Override
    public List<Others> getOthersList() {
        return othersDao.selectOthersList();
    }

    // 发布物品
    @Override
    public int publishOthers(Others others) {
        return othersDao.insertOthers(others);
    }


}
