package com.dinerReview.dinerAPI.controller;

import com.dinerReview.dinerAPI.model.DiningReview;
import com.dinerReview.dinerAPI.model.Restaurant;
import com.dinerReview.dinerAPI.model.User;
import com.dinerReview.dinerAPI.repository.DiningReviewRepository;
import com.dinerReview.dinerAPI.repository.RestaurantRepository;
import com.dinerReview.dinerAPI.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


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

    @GetMapping("/restaurants") //NOT WORKING
    public Iterable<Restaurant> getAllRestaurants(){
    return this.restaurantRepository.findAll();
    }

    @GetMapping("/restaurant") //NOT WORKING
    public Restaurant getRestaurant(@RequestParam Long id){
        Optional<Restaurant> restaurantOptional = this.restaurantRepository.getById(id);
        if(restaurantOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Restaurant not found");
        }
        Restaurant restaurant = restaurantOptional.get();
        return  restaurant;
    }

    @PostMapping("/restaurant") // WORKING ALLRIGHT
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant){
        Restaurant newRestaurant = this.restaurantRepository.save(restaurant);
        return newRestaurant;
    }

    @PutMapping("/restaurant/{id}") // NOT TESTED YET
    public Restaurant editRestaurant(@RequestBody Restaurant restaurant,@PathVariable Long id){
        Optional<Restaurant> restaurantOptional = this.restaurantRepository.getById(id);
        if(restaurantOptional.isEmpty()){
            return null;
        }
        Restaurant restaurantToUpdate = restaurantOptional.get();
        if(restaurant.getName()!= null){
            restaurantToUpdate.setName(restaurant.getName());
        }
        if(restaurant.getZipCode()!= null){
            restaurantToUpdate.setZipCode(restaurant.getZipCode());
        }
        return this.restaurantRepository.save(restaurantToUpdate);
    }

    @GetMapping("/users")
    public Iterable<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    @GetMapping("/user")
    public User getUser(@RequestParam String name){
        Optional<User> userOptional = this.userRepository.getByName(name);
        if(userOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found");
        }
        User user = userOptional.get();
        return user;
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user){
        User newUser = this.userRepository.save(user);
        return newUser;
    }

    @GetMapping("/diningReviews")
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

    @PostMapping("/diningReview")
        public DiningReview createDiningReview(@RequestBody DiningReview diningReview){
        DiningReview newDiningReview = this.diningReviewRepository.save(diningReview);
        return newDiningReview;
    }





}
