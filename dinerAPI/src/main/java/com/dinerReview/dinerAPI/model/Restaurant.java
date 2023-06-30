package com.dinerReview.dinerAPI.model;

import javax.persistence.*;

import lombok.*;

import java.util.ArrayList;

@Entity
@Table(name="RESTAURANT")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NAME", nullable = false)
    private String name;

    @Column(name="ZIPCODE")
    private Integer zipCode;

    @Column(name="PEANUTSCORE")
    private String peanutScore;

    @Column(name="EGGSCORE")
    private String eggScore;

    @Column(name="DAIRYSCORE")
    private String dairyScore;

    @Column(name="TOTALSCORE")
    private String totalScore;


}

