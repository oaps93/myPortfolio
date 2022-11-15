package com.codecademy.goldmedal.repository;

import com.codecademy.goldmedal.model.GoldMedal;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface GoldMedalListRepository extends CrudRepository<GoldMedal,Integer> {

    public List<GoldMedal> findAll();
    public List<GoldMedal> findByCountry(String country);
    public List<GoldMedal> findByCountryAndGender(String country, String gender);
    public List<GoldMedal> findByCountryOrderByYear(String country);
    public List<GoldMedal> findByCountryOrderByYearDesc(String country);
    public List<GoldMedal> findByCountryOrderBySeason(String country);
    public List<GoldMedal> findByCountryOrderBySeasonDesc(String country);
    public List<GoldMedal> findByCountryOrderByCity(String country);
    public List<GoldMedal> findByCountryOrderByCityDesc(String country);
    public List<GoldMedal> findByCountryOrderByName(String country);
    public List<GoldMedal> findByCountryOrderByNameDesc(String country);
    public List<GoldMedal> findByCountryOrderByEvent(String country);
    public List<GoldMedal> findByCountryOrderByEventDesc(String country);
    public List<GoldMedal> findByCountryAndSeasonOrderByYear(String country, String Season);
    public List<GoldMedal> findByCountryAndSeason(String country, String Season);

    public List<GoldMedal> findBySeason(String Season);
    public List<GoldMedal> findBySeasonOrderByYear(String Season);












}
