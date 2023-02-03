package com.dinerReview.dinerAPI.repository;

import com.dinerReview.dinerAPI.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant,Integer> {

    Optional<Restaurant> getByName(String name);
    Iterable<Restaurant> findAll();

    Optional<Restaurant> getByNameAndZipCode(String name, Integer zipCode);

    Restaurant save(Restaurant newRestaurant);

    Optional<Restaurant> getById(Long id);

    Iterable<Restaurant> getByZipCodeAndPeanutScoreGreaterThanOrderByNameDesc(Integer zipCode, Integer value);
    Iterable<Restaurant> getByZipCodeAndEggScoreGreaterThanOrderByNameDesc(Integer zipCode, Integer value);
    Iterable<Restaurant> getByZipCodeAndDairyScoreGreaterThanOrderByNameDesc(Integer zipCode, Integer value);


}
