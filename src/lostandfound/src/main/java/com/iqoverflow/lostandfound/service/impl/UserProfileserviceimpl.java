package com.iqoverflow.lostandfound.service.impl;

import com.iqoverflow.lostandfound.dao.UserProfiledao;
import com.iqoverflow.lostandfound.domain.User;
import com.iqoverflow.lostandfound.service.UserProfileservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileserviceimpl implements UserProfileservice {


    @Autowired
    private UserProfiledao userProfiledao;

    @Override
    public User getUserProfile(String id) {
        User userProfile = userProfiledao.getUserProfile(id);
        return userProfile;
    }

    @Override
    public void setUserProfile(User user) {
        userProfiledao.setUserProfile(user);
    }

    @Override
    public void setUserContact(String uID, String contact) {
        userProfiledao.setUserContact(uID,contact);
    }
}
