package com.sprk.sprk_hotels.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @NotBlank(message = "please provide username")
    private String userName;

    @NotBlank(message = "please provide email")
    @Email(message = "incorrect email")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "please enter password")
    private String password;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = {
            @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    private Set<RoleModel> roles;

}