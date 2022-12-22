package com.dinerReview.dinerAPI.repository;

import com.dinerReview.dinerAPI.model.User;
import org.springframework.data.repository.CrudRepository;


import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> getByName(String name);
    User save(User newUser);
    Iterable<User> findAll();




}
