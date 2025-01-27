package com.sprk.sprk_hotels.config;

import com.sprk.sprk_hotels.model.RoleModel;
import com.sprk.sprk_hotels.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;



@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (roleRepository.findByName("ROLE_USER").isEmpty()) {
            RoleModel roleModel = new RoleModel();
            roleModel.setName("ROLE_USER");
            roleRepository.save(roleModel);
        }

    }

}