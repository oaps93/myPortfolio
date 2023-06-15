package com.dinerReview.dinerAPI.model;

import javax.persistence.*;

import lombok.*;

import java.util.HashSet;

@Entity
@Table(name="USER_")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NAME", nullable = false)
    private String name;

    @Column(name="CITY")
    private String city;

    @Column(name="STATE")
    private String state;

    @Column(name="ZIPCODE")
    private Integer zipCode;

    @Column(name="PEANUT_ALLERGY")
    private Boolean peanutAllergy;
    
    @Column(name="EGG_ALLERGY")
    private Boolean eggAllergy;

    @Column(name="DAIRY_ALLERGY")
    private Boolean dairyAllergy;


}
