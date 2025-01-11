package com.sprk.sprk_hotels.repository;

import com.sprk.sprk_hotels.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
