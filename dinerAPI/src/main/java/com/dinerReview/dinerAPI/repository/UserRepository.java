package com.dinerReview.dinerAPI.repository;

import com.dinerReview.dinerAPI.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> getByUser(String user);
    User save(User newUser);




}
