package com.dinerReview.dinerAPI.model;

import javax.persistence.*;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;

@Entity
@Table(name="RESTAURANT")
@Setter
@Getter
@RequiredArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="ZIPCODE")
    private Integer zipCode;

    @Column(name="PEANUTSCORE")
    private int peanutScore;

    @Column(name="EGGSCORE")
    private int eggScore;

    @Column(name="DAIRYSCORE")
    private int dairyScore;

    @Column(name="TOTALSCORE")
    private int totalScore;


}

