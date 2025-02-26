package com.sprk.sprk_hotels.controller;

import com.sprk.sprk_hotels.model.Listing;
import com.sprk.sprk_hotels.model.User;
import com.sprk.sprk_hotels.service.ListingService;
import com.sprk.sprk_hotels.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ListingService listingService;

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            System.out.println("🔍 Attempting login for: " + username);
            System.out.println("🔑 Entered Password: " + password);

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println("✅ Authentication successful! Redirecting...");
            return "redirect:/dashboard";

        } catch (BadCredentialsException e) {
            System.out.println("❌ Authentication failed: Invalid username or password.");
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }


    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";  // Make sure register.html or register.jsp exists
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        System.out.println("Register endpoint hit!");
        System.out.println("Received User Data: " + user);

        if (result.hasErrors()) {
            System.out.println("Validation Errors: " + result.getAllErrors());
            return "register";
        }

        System.out.println("Saving user: " + user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash password
        userService.saveUser(user);

        model.addAttribute("successMessage", "Registration successful! Please login.");
        return "redirect:/login";
    }


    @GetMapping("/dashboard")
    public String showDashboard(Model model){
        List<Listing>listings= listingService.getAllListings();
        model.addAttribute("allListings", listings);
        return "dashboard";
    }

    @GetMapping("/user/view/{id}")
    public String showHotelById(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        Listing listing = listingService.getListingById(id);

        if (listing != null) {
            model.addAttribute("listing", listing);
            return "user-show-listing";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Your listing not found in our record!");
            return "redirect:/listings";
        }
    }



//    @GetMapping("/listings/book")
//    public String bookListing(@RequestParam("id") int id, Model model) {
//        Listing listing = listingService.getListingById(id);
//        model.addAttribute("listing", listing);
//        return "payment"; // Redirect to the booking page
//    }

    @GetMapping("/listings/book")
    public String bookListing(@RequestParam("id") int id, Model model) {
        Listing listing = listingService.getListingById(id);

        // Example values, replace with actual logic
        String qrCodeUrl = "/images/qrcode.png"; // Set the QR code URL
        double amount = listing.getPrice();  // Assuming Listing has a getPrice() method

        model.addAttribute("listing", listing);
        model.addAttribute("amount", amount);
        model.addAttribute("qrCode", qrCodeUrl); // Pass QR Code URL

        return "payment"; // Render payment.html
    }

}