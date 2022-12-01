package com.dinerReview.dinerAPI.repository;

import com.dinerReview.dinerAPI.model.DiningReview;
import com.dinerReview.dinerAPI.model.Status;
import org.springframework.data.repository.CrudRepository;

public interface DiningReviewRepository extends CrudRepository<DiningReview,Integer> {

    DiningReview save(DiningReview newDiningReview);
    Iterable<DiningReview> getByStatus(Status status);

}