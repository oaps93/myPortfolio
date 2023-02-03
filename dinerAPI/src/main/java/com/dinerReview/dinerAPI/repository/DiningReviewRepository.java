package com.dinerReview.dinerAPI.repository;

import com.dinerReview.dinerAPI.model.DiningReview;
import com.dinerReview.dinerAPI.model.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DiningReviewRepository extends CrudRepository<DiningReview,Integer> {

    DiningReview save(DiningReview newDiningReview);
    Iterable<DiningReview> getByStatus(Status status);

    List<DiningReview> getAllByStatusAndRestaurantId(Status status, Long restId);
    List<DiningReview> getAllByStatusAndRestaurantIdAndPeanutScoreGreaterThan(Status status, Long restId, int score);
    List<DiningReview> getAllByStatusAndRestaurantIdAndEggScoreGreaterThan(Status status, Long restId, int score);

    List<DiningReview> getAllByStatusAndRestaurantIdAndDairyScoreGreaterThan(Status status, Long restId, int score);

    Optional<DiningReview> getById(Long id);

}
