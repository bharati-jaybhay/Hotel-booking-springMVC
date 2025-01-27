package com.sprk.sprk_hotels.service;

import com.sprk.sprk_hotels.model.Listing;
import com.sprk.sprk_hotels.model.UserModel;

import java.util.List;

public interface UserService {

    UserModel getUserByEmail(String email);

    UserModel saveUserModel(UserModel userModel);
}
