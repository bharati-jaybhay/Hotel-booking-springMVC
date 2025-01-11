package com.sprk.sprk_hotels.controller;

import com.sprk.sprk_hotels.model.Listing;
import com.sprk.sprk_hotels.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/listings/view")
    public String showHotels(Model model) {
        List<Listing> listings = userService.getAllListingsForUSer();
        model.addAttribute("allListings", listings);

        return "user-index";
    }
}
