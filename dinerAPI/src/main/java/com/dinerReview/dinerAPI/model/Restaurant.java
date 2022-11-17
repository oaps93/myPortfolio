package com.dinerReview.dinerAPI.model;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

@Entity
@Table(name="RESTAURANT")
public class Restaurant {
    @Id
    @GeneratedValue
    private long id;

    @Column(name="NAME")
    private String name;

    @Column(name="TYPE")
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

