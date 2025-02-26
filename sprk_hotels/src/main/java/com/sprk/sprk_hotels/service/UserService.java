package com.sprk.sprk_hotels.service;

import com.sprk.sprk_hotels.model.User;
import jakarta.validation.Valid;

public interface UserService {

    User saveUser( User user);

    User findByUsername( String username);


}
