package com.dinerReview.dinerAPI.controller;

import com.dinerReview.dinerAPI.model.DiningReview;
import com.dinerReview.dinerAPI.model.Restaurant;
import com.dinerReview.dinerAPI.model.User;
import com.dinerReview.dinerAPI.repository.DiningReviewRepository;
import com.dinerReview.dinerAPI.repository.RestaurantRepository;
import com.dinerReview.dinerAPI.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Entity;
import java.util.DuplicateFormatFlagsException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/diner")
public class DinerReviewController {

    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final DiningReviewRepository diningReviewRepository;

    public DinerReviewController(UserRepository userRepository, RestaurantRepository restaurantRepository, DiningReviewRepository diningReviewRepository){
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.diningReviewRepository = diningReviewRepository;
    }

    @GetMapping("/restaurant")
    public Iterable<Restaurant> getAllRestaurants(){
        return this.restaurantRepository.findAll();
    }

    @GetMapping("/restaurant")
    public Restaurant getRestaurant(@RequestParam Long id){
        Optional<Restaurant> restaurantOptional = this.restaurantRepository.getById(id);
        if(restaurantOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Restaurant not found");
        }
        Restaurant restaurant = restaurantOptional.get();
        return  restaurant;
    }

    @GetMapping("/user")
    public Iterable<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    @GetMapping("/user")
    public User getUser(@RequestParam String name){
        Optional<User> userOptional = this.userRepository.getByUser(name);
        if(userOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found");
        }
        User user = userOptional.get();
        return user;
    }

    @GetMapping("/diningReview")
    public Iterable<DiningReview> getAllDiningReview(){
        return this.diningReviewRepository.findAll();
    }

    @GetMapping("/diningReview")
    public DiningReview getDiningReview(@RequestParam Long id){
        Optional<DiningReview> diningReviewOptional = this.diningReviewRepository.getById(id);

        if(diningReviewOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Review not submitted");
        }
        DiningReview diningReview = diningReviewOptional.get();
        return  diningReview;

    }





}
