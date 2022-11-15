package com.codecademy.goldmedal.repository;

import com.codecademy.goldmedal.model.Country;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CountryRepository extends CrudRepository<Country,Integer> {

    public  List<Country> findAll();
    public List<Country> findAll(Sort sort);
    public Optional<Country> findByName(String name);









}
