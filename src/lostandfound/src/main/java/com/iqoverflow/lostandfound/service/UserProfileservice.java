package com.iqoverflow.lostandfound.service;

import com.iqoverflow.lostandfound.domain.User;

public interface UserProfileservice {
     User getUserProfile(String id);
     void setUserProfile(User user);

     //插入用户的联系方式
     void setUserContact(String uID,String contact);
}
