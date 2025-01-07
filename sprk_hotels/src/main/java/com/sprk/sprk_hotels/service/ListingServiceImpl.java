package com.sprk.sprk_hotels.service;

import org.springframework.stereotype.Service;

import com.sprk.sprk_hotels.model.Listing;
import com.sprk.sprk_hotels.repository.ListingRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ListingServiceImpl implements ListingService{

    private ListingRepository listingRepository;

    @Override
    public Listing saveListing(Listing listing) {
        Listing savedListing = listingRepository.save(listing);
        return savedListing;
    }
}
