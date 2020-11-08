package com.iqoverflow.lostandfound.service;

import com.iqoverflow.lostandfound.domain.User;

public interface UserProfileservice {
     User getUserProfile(String id);
     void setUserProfile(User user);
}
