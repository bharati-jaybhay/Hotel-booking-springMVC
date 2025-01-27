package com.sprk.sprk_hotels.service;

import com.sprk.sprk_hotels.model.Listing;

import java.util.List;

public interface ListingService {

    Listing saveListing(Listing listing);

    List<Listing> getAllListings();

    Listing getListingById(int id);

    void deleteListingById(int id);
}
