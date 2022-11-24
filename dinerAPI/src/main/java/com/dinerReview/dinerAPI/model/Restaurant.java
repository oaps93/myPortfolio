package com.dinerReview.dinerAPI.model;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.NonNull;

@Entity
@Table(name="RESTAURANT")
@Getter
@Setter
@RequiredArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue
    private long id;

    @Column(name="NAME")
    @NonNull
    private String name;

    @Column(name="TYPE")
    @NonNull
    private String type;

    @Column(name="PEANUTSCORE")
    private int peanutScore;

    @Column(name="EGGSCORE")
    private int eggScore;

    @Column(name="DAIRYSCORE")
    private int dairyScore;

    @Column(name="TOTALSCORE")
    private int totalScore;


}

