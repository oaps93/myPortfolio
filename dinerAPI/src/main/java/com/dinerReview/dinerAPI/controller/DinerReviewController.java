package com.dinerReview.dinerAPI.controller;

import com.dinerReview.dinerAPI.model.*;
import com.dinerReview.dinerAPI.repository.DiningReviewRepository;
import com.dinerReview.dinerAPI.repository.RestaurantRepository;
import com.dinerReview.dinerAPI.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.text.DecimalFormat;
import java.util.Optional;
import java.util.*;

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
    @GetMapping("/admin/pendingReviews") //WORKING FINE
    public Iterable<DiningReview> getPendingDinningReviews(){
        return this.diningReviewRepository.getByStatus(Status.PENDING);
    }


    @PutMapping("/admin/pendingReviews/{id}") // WORKING FINE
    public DiningReview reviewsAcceptance(@PathVariable Long id, @RequestBody AdminReviewAction reviewAction){

        Optional<DiningReview> reviewOptional = this.diningReviewRepository.getById(id);

        if(reviewOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Not submitted review");
        }
        DiningReview diningReviewToUpdate = reviewOptional.get();

        // Re-computing scores

        if(reviewAction.getReviewAccepted()){
            diningReviewToUpdate.setStatus(Status.ACCEPTED);
            diningReviewToUpdate = this.diningReviewRepository.save(diningReviewToUpdate);
            recomputeScores(diningReviewToUpdate.getRestaurantId());

        }
        else{
            diningReviewToUpdate.setStatus(Status.DECLINED);
            diningReviewToUpdate = this.diningReviewRepository.save(diningReviewToUpdate);
        }
        return diningReviewToUpdate;
    }

    public void recomputeScores(Long restId) {

        Optional<Restaurant> restaurantScoresUpdateOptional = this.restaurantRepository.getById(restId);
        Restaurant restaurantScoresUpdate = restaurantScoresUpdateOptional.get();

        List<DiningReview> peanutScoreList = this.diningReviewRepository.getAllByStatusAndRestaurantIdAndPeanutScoreGreaterThan(Status.ACCEPTED,restId,0);
        List<DiningReview> eggsScoreList = this.diningReviewRepository.getAllByStatusAndRestaurantIdAndEggScoreGreaterThan(Status.ACCEPTED,restId,0);
        List<DiningReview> dairyScoreList = this.diningReviewRepository.getAllByStatusAndRestaurantIdAndDairyScoreGreaterThan(Status.ACCEPTED,restId,0);

        int totalReviewsPeanutScore = peanutScoreList.size();
        int totalReviewsEggScore = eggsScoreList.size();
        int totalReviewsDairyScore = dairyScoreList.size();

        double sumAllPeanutScores = 0;
        double sumAllEggScores = 0;
        double sumAllDairyScores = 0;

        for(DiningReview diningReview: peanutScoreList) {
            sumAllPeanutScores += diningReview.getPeanutScore();
        }
        for(DiningReview diningReview: eggsScoreList) {
            sumAllEggScores += diningReview.getEggScore();
        }
        for(DiningReview diningReview: dairyScoreList) {
            sumAllDairyScores += diningReview.getDairyScore();
        }

        double avgPeanutScore = 0.00;
        double avgEggScore = 0.00;
        double avgDairyScore = 0.00;


        if(totalReviewsPeanutScore != 0)
            avgPeanutScore = sumAllPeanutScores / totalReviewsPeanutScore;
        if(totalReviewsEggScore != 0)
            avgEggScore = sumAllEggScores / totalReviewsEggScore;
        if(totalReviewsDairyScore != 0)
            avgDairyScore = sumAllDairyScores / totalReviewsDairyScore;

        double totalAvgScores = (avgDairyScore + avgEggScore + avgPeanutScore) / 3;

        // Giving a format
        String format = "0.00";
        DecimalFormat decimalFormat = new DecimalFormat(format);

        restaurantScoresUpdate.setPeanutScore(decimalFormat.format(avgPeanutScore));
        restaurantScoresUpdate.setDairyScore(decimalFormat.format(avgDairyScore));
        restaurantScoresUpdate.setEggScore(decimalFormat.format(avgEggScore));
        restaurantScoresUpdate.setTotalScore(decimalFormat.format(totalAvgScores));

        this.restaurantRepository.save(restaurantScoresUpdate);
    }

    @GetMapping("/restaurants") //WORKING FINE
    public Iterable<Restaurant> getAllRestaurants(){
    return this.restaurantRepository.findAll();
    }

    @GetMapping("/restaurants/specialSearch")
    public Iterable<Restaurant> getSpecialSearch(@RequestParam Integer zipCode, @RequestParam String allergy){
        switch (allergy){
            case "peanut":
                return this.restaurantRepository.getByZipCodeAndPeanutScoreGreaterThanOrderByNameDesc(zipCode,0);
            case "egg":
                return this.restaurantRepository.getByZipCodeAndEggScoreGreaterThanOrderByNameDesc(zipCode,0);
            case "diary":
                return this.restaurantRepository.getByZipCodeAndDairyScoreGreaterThanOrderByNameDesc(zipCode,0);
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Allergy not found");
        }
    }

    @GetMapping("/restaurant") //WORKING FINE
    public Restaurant getRestaurant(@RequestParam Long id){
        Optional<Restaurant> restaurantOptional = this.restaurantRepository.getById(id);
        if(restaurantOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Restaurant not found");
        }
        Restaurant restaurant = restaurantOptional.get();
        return  restaurant;
    }
    @GetMapping("/restaurant/{id}/acceptedReviews")
    public List<DiningReview> getAcceptedDinningReviews(@PathVariable Long id){
        return this.diningReviewRepository.getAllByStatusAndRestaurantId(Status.ACCEPTED, id);
    }
    @PostMapping("/restaurant") // WORKING FINE
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant){
        if(this.restaurantRepository.getByNameAndZipCode(restaurant.getName(), restaurant.getZipCode()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicated restaurant");
        }
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

        if(this.userRepository.getByName(diningReview.getName()).isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User doesn't exist");
        }
        if(this.restaurantRepository.getById(diningReview.getRestaurantId()).isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Restaurant not found");
        }
        DiningReview newDiningReview = this.diningReviewRepository.save(diningReview);
        return newDiningReview;

    }





}
