package com.dinerReview.dinerAPI.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;

@Entity
@Table(name="USER")
@Setter
@Getter
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="USER")
    @NonNull
    private HashSet<String> user;

    @Column(name="CITY")
    private String city;

    @Column(name="STATE")
    private String state;

    @Column(name="ZIPCODE")
    private Integer zipcode;

    @Column(name="PEANUTALLERGY")
    private Boolean peanutAllergy;
    
    @Column(name="EGGALLERGY")
    private Boolean eggAllergy;

    @Column(name="DAIRYALLERGY")
    private Boolean dairyAllergy;


}
