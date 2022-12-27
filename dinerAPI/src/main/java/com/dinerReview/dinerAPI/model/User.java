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
import java.util.HashSet;

@Entity
@Table(name="USER_")
@Setter
@Getter
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="CITY")
    private String city;

    @Column(name="STATE")
    private String state;

    @Column(name="ZIPCODE")
    private Integer zipCode;

    @Column(name="PEANUTALLERGY")
    private Boolean peanutAllergy;
    
    @Column(name="EGGALLERGY")
    private Boolean eggAllergy;

    @Column(name="DAIRYALLERGY")
    private Boolean dairyAllergy;


}
