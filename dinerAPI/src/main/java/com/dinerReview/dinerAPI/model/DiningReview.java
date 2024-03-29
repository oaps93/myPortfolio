package com.dinerReview.dinerAPI.model;


import lombok.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Entity
@Table(name="REVIEWS")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiningReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="RESTID")
    private Long restaurantId;

    @Column(name="PEANUTSCORE")
    private int peanutScore;

    @Column(name="EGGSCORE")
    private int eggScore;

    @Column(name="DAIRYSCORE")
    private int dairyScore;

    @Column(name="COMMENTARY")
    private String commentary;

    @Enumerated(EnumType.STRING)
    @Column(name="STATUS")
    private Status status = Status.PENDING;
}
