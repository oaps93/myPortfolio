package com.dinerReview.dinerAPI.model;


import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="REVIEWS")
@Setter
@Getter
@RequiredArgsConstructor
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
