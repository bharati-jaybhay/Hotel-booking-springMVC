package com.sprk.sprk_hotels.service;

import com.sprk.sprk_hotels.model.Listing;
import com.sprk.sprk_hotels.repository.ListingRepository;
import com.sprk.sprk_hotels.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final ListingRepository listingRepository;
    private final UserRepository userRepository;

    // Constructor for dependency injection
    public UserServiceImpl(ListingRepository listingRepository, UserRepository userRepository) {
        this.listingRepository = listingRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Listing> getAllListingsForUSer() {

        return listingRepository.findAll();
    }
}
