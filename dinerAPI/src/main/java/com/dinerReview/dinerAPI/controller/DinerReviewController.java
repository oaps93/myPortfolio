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

    @GetMapping("/restaurants") //WORKING
    public Iterable<Restaurant> getAllRestaurants(){
    return this.restaurantRepository.findAll();
    }

    @GetMapping("/restaurant") //WORKING
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

    @PutMapping("/restaurant/{id}") // WORKING FINE
    public Restaurant editRestaurant(@RequestBody Restaurant restaurant,@PathVariable Long id){
        Optional<Restaurant> restaurantOptional = this.restaurantRepository.getById(id);
        if(restaurantOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"NOT FOUND");
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

    @GetMapping("/users") // WORKING FINE
    public Iterable<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    @GetMapping("/user") // WORKING FINE
    public User getUser(@RequestParam String name){
        Optional<User> userOptional = this.userRepository.getByName(name);
        if(userOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found");
        }
        User user = userOptional.get();
        return user;
    }
    @PostMapping("/user") // WORKING FINE
    public User createUser(@RequestBody User user){

        if(this.userRepository.getByName(user.getName()).isEmpty()){

            User newUser = this.userRepository.save(user);
            return newUser;

        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"USER ALREADY GIVEN");

    }

    @PutMapping("/user/{name}") // WORKING FINE
    public User editUser(@RequestBody User user,@PathVariable String name){
        Optional<User> userOptional = this.userRepository.getByName(name);

        if(userOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"NOT FOUND");
        }
        User userToUpdate = userOptional.get();

        if(user.getCity()!= null){
            userToUpdate.setCity(user.getCity());
        }
        if(user.getState()!= null){
            userToUpdate.setState(user.getState());
        }
        if(user.getZipCode()!= null){
            userToUpdate.setZipCode(user.getZipCode());
        }
        if(user.getPeanutAllergy()!= null){
            userToUpdate.setPeanutAllergy(user.getPeanutAllergy());
        }
        if(user.getEggAllergy()!= null){
            userToUpdate.setEggAllergy(user.getEggAllergy());
        }
        if(user.getDairyAllergy()!= null){
            userToUpdate.setDairyAllergy(user.getDairyAllergy());
        }
        return this.userRepository.save(userToUpdate);
    }

    @GetMapping("/diningReviews") // WORKING FINE
    public Iterable<DiningReview> getAllDiningReview(){
        return this.diningReviewRepository.findAll();
    }

    @GetMapping("/diningReview") // WORKING FINE
    public DiningReview getDiningReview(@RequestParam Long id){
        Optional<DiningReview> diningReviewOptional = this.diningReviewRepository.getById(id);

        if(diningReviewOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Review not submitted");
        }
        DiningReview diningReview = diningReviewOptional.get();
        return  diningReview;

    }

    @PostMapping("/diningReview") // WORKING FINE
        public DiningReview createDiningReview(@RequestBody DiningReview diningReview){
        if(this.userRepository.getByName(diningReview.getName()).isPresent()){
            DiningReview newDiningReview = this.diningReviewRepository.save(diningReview);
            return newDiningReview;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User doesn't exist");

    }


}
