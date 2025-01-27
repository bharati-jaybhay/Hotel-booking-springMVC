package com.sprk.sprk_hotels.service;

import com.sprk.sprk_hotels.model.RoleModel;
import com.sprk.sprk_hotels.model.UserModel;
import com.sprk.sprk_hotels.repository.RoleRepository;
import com.sprk.sprk_hotels.repository.UserRepository;
import com.sprk.sprk_hotels.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserModel getUserByEmail(String email) {
        UserModel dbUserModel = userRepository.findByEmail(email);
        return dbUserModel;
    }

    @Override
    public UserModel saveUserModel(UserModel userModel) {

        RoleModel roleModel = roleRepository.findByName("ROLE_USER").get();

        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));

        userModel.setRoles(Set.of(roleModel));
        UserModel savedUser = userRepository.save(userModel);
        return savedUser;
    }
}