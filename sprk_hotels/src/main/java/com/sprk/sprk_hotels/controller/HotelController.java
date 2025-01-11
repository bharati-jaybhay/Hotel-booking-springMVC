package com.sprk.sprk_hotels.controller;

import com.sprk.sprk_hotels.service.ListingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.sprk.sprk_hotels.model.Listing;

import jakarta.validation.Valid;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.regex.Pattern;

@Controller
@AllArgsConstructor
public class HotelController {

    private ListingService listingService;

    @GetMapping("/listings/add")
    public String showAddForm(Model model) {
        Listing listing = new Listing();
        model.addAttribute("listing", listing);
        model.addAttribute("btn_name", "Save");
        return "listing-form";
    }

    @PostMapping("/listings")
    public String saveListing(@RequestParam(name = "id") String idStr, @Valid @ModelAttribute Listing listing, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // Handling Server Side validation
        if (bindingResult.hasErrors()) {
            return "listing-form";
        }
        String regex = "^\\d+$";
        if (Pattern.matches(regex, idStr)) {
            // Save to DB
            int id = Integer.parseInt(idStr);
            listing.setId(id);
            listingService.saveListing(listing);
            if (id != 0) {
                redirectAttributes.addFlashAttribute("successMessage", "Your listing updated successfully");
                return "redirect:/listings/view/"+id;
            } else {
                redirectAttributes.addFlashAttribute("successMessage", "Your listing added successfully");
                return "redirect:/listings/add";
            }


        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Id cannot be string!");
            return "redirect:/listings";
        }


    }

    // Show all hotels list
    @GetMapping(value = {"/", "/listings"})
    public String showAllHotels(Model model) {
        List<Listing> listings = listingService.getAllListings();
        model.addAttribute("allListings", listings);

        return "index";
    }

    // Show hotel by id
    @GetMapping("/listings/view/{id}")
    public String showHotelById(@PathVariable(name = "id") String idStr, Model model, RedirectAttributes redirectAttributes) {

        String regex = "^\\d+$";
        if (Pattern.matches(regex, idStr)) {
            int id = Integer.parseInt(idStr);
            Listing listing = listingService.getListingById(id);

            if (listing != null) {
                model.addAttribute("listing", listing);
                return "show-listing";
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Your listing not found in our record!");
                return "redirect:/listings";
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Id cannot be string!");
            return "redirect:/listings";
        }
    }

    // delete by id
    @GetMapping("/listings/delete")
    public String deleteHotelById(@RequestParam(name = "id") String idStr, RedirectAttributes redirectAttributes) {

        String regex = "^\\d+$";
        if (Pattern.matches(regex, idStr)) {
            int id = Integer.parseInt(idStr);
            Listing listing = listingService.getListingById(id);

            if (listing != null) {
                // delete
                listingService.deleteListingById(id);
                redirectAttributes.addFlashAttribute("errorMessage", "Listing Deleted Successfully!");
                return "redirect:/listings";
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Your listing not found in our record!");
                return "redirect:/listings";
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Id cannot be string!");
            return "redirect:/listings";
        }
    }

    // update by id
    @GetMapping("/listings/edit")
    public String showUpdateForm(@RequestParam(name = "id") String idStr, RedirectAttributes redirectAttributes, Model model) {

        String regex = "^\\d+$";
        if (Pattern.matches(regex, idStr)) {
            int id = Integer.parseInt(idStr);
            Listing listing = listingService.getListingById(id);

            if (listing != null) {
                model.addAttribute("listing", listing);
                model.addAttribute("btn_name", "Update");
                return "listing-form";

            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Your listing not found in our record!");
                return "redirect:/listings";
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Id cannot be string!");
            return "redirect:/listings";
        }
    }

}