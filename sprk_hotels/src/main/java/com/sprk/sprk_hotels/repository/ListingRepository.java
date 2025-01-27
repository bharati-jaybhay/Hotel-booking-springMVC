package com.sprk.sprk_hotels.repository;

import com.sprk.sprk_hotels.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingRepository extends JpaRepository<Listing,Integer> {
}
