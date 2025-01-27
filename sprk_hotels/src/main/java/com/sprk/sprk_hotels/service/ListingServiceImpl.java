package com.sprk.sprk_hotels.service;

import com.sprk.sprk_hotels.model.Listing;
import com.sprk.sprk_hotels.repository.ListingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ListingServiceImpl implements ListingService{

    private ListingRepository listingRepository;

    @Override
    public Listing saveListing(Listing listing) {
        Listing savedListing = listingRepository.save(listing);
        return savedListing;
    }

    @Override
    public List<Listing> getAllListings() {
        return listingRepository.findAll();

    }

    @Override
    public Listing getListingById(int id) {
        return listingRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteListingById(int id) {
        listingRepository.deleteById(id);
    }

}