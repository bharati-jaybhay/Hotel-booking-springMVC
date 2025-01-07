package com.sprk.sprk_hotels.controller;

import com.sprk.sprk_hotels.service.ListingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sprk.sprk_hotels.model.Listing;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class HotelController {

    private ListingService listingService;

    @GetMapping("/listings/add")
    public String showAddForm(Model model) {
        Listing listing = new Listing();
        model.addAttribute("listing", listing);
        return "listing-form";
    }

    @PostMapping("/listings")
    public String saveListing(@Valid @ModelAttribute Listing listing, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // Handling Server Side validation
        if (bindingResult.hasErrors()) {
            return "listing-form";
        }

        // Save to DB
        listingService.saveListing(listing);
        redirectAttributes.addFlashAttribute("successMessage", "Your listing added successfully");

        return "redirect:/listings/add";
    }

}
