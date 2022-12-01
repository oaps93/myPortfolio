package com.dinerReview.dinerAPI.repository;

import com.dinerReview.dinerAPI.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant,Integer> {

    Optional<Restaurant> getByName(String name);

    Optional<Restaurant> getByZipCode(String zipCode);

    Restaurant save(Restaurant newRestaurant);

    Restaurant getById(Long id);

    Iterable<Restaurant> getByZipCodeAndPeanutScoreGreaterThanOrderByNameDesc(String zipCode, Integer value);
}
