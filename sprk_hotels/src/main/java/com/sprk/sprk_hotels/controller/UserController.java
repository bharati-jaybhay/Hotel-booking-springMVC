package com.sprk.sprk_hotels.controller;

import com.sprk.sprk_hotels.model.UserModel;
import com.sprk.sprk_hotels.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/listings/signup")
    public String signupForm(Model model) {

        UserModel userModel = new UserModel();

        model.addAttribute("userModel", userModel);


        return "signup";

    }

    @PostMapping("/listings/signup")
    public String processSignupForm(@Valid @ModelAttribute("userModel") UserModel userModel,
                                    BindingResult bindingResult, RedirectAttributes redirectAttributes)
            throws Exception {

        if (bindingResult.hasErrors()) {
            return "signup";
        }
        UserModel existingModel = userService.getUserByEmail(userModel.getEmail());

        if (existingModel != null && userModel.getUserId() == 0) {

            String message = "user with email: " + userModel.getEmail() + " already exists";
            redirectAttributes.addFlashAttribute("errorMessage", message);
            return "redirect:/listings/signup";
        } else {
            // insert
            userService.saveUserModel(userModel);
            redirectAttributes.addFlashAttribute("successMessage", "User saved successfully");
            return "redirect:/listings/login";

        }
    }


}