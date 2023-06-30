package com.dinerReview.dinerAPI.repository;

import com.dinerReview.dinerAPI.model.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.Table;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RestaurantRepositoryTest {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Test
    public void restaurantRepository_SaveTest(){

        Restaurant restaurant = Restaurant.builder()
                .name("Canibal")
                .zipCode(45135)
                .build();


        Restaurant restaurantSaved = restaurantRepository.save(restaurant);

        assertEquals(restaurant.getName(),restaurantSaved.getName());

    }

    @Test
    public void restaurantRepository_NotSavedTest(){

        Restaurant restaurant = Restaurant.builder()
                .zipCode(45135)
                .build();


        assertThrows(Exception.class,() -> restaurantRepository.save(restaurant));

    }

    @Test
    public void restaurantRepository_FindAllTest(){

        Restaurant restaurant1 = Restaurant.builder()
                .name("Canibal")
                .zipCode(45135)
                .build();
        Restaurant restaurant2 = Restaurant.builder()
                .name("Antonia")
                .zipCode(42135)
                .build();
        Restaurant restaurant3 = Restaurant.builder()
                .name("Berenjena")
                .zipCode(45120)
                .build();


        Restaurant restaurant1Saved = restaurantRepository.save(restaurant1);
        Restaurant restaurant2Saved = restaurantRepository.save(restaurant2);
        Restaurant restaurant3Saved = restaurantRepository.save(restaurant3);

        Iterable<Restaurant> restaurantIterable = restaurantRepository.findAll();
        List<Restaurant> restaurantList = new LinkedList<>();

        restaurantIterable.forEach( r -> restaurantList.add(r));

        assertEquals(3,restaurantList.size());

    }

    @Test
    public void restaurantRepository_GetByIdTest(){

        Restaurant restaurant1 = Restaurant.builder()
                .name("Canibal")
                .zipCode(45135)
                .build();
        Restaurant restaurant2 = Restaurant.builder()
                .name("Antonia")
                .zipCode(42135)
                .build();
        Restaurant restaurant3 = Restaurant.builder()
                .name("Berenjena")
                .zipCode(45120)
                .build();


        Restaurant restaurant1Saved = restaurantRepository.save(restaurant1);
        Restaurant restaurant2Saved = restaurantRepository.save(restaurant2);
        Restaurant restaurant3Saved = restaurantRepository.save(restaurant3);


        assertEquals(restaurantRepository.getById(2l).get(),restaurant2Saved);
        assertThrows(Exception.class, () -> restaurantRepository.getById(99l).get());

    }

    @Test
    public void restaurantRepository_GetByNameAndZipCodeTest(){

        Restaurant restaurant1 = Restaurant.builder()
                .name("Canibal")
                .zipCode(45135)
                .build();
        Restaurant restaurant2 = Restaurant.builder()
                .name("Antonia")
                .zipCode(42135)
                .build();
        Restaurant restaurant3 = Restaurant.builder()
                .name("Berenjena")
                .zipCode(45120)
                .build();


        Restaurant restaurant1Saved = restaurantRepository.save(restaurant1);
        Restaurant restaurant2Saved = restaurantRepository.save(restaurant2);
        Restaurant restaurant3Saved = restaurantRepository.save(restaurant3);


        assertEquals(restaurantRepository.getByNameAndZipCode("Antonia",42135).get(),restaurant2Saved);
        assertThrows(Exception.class, () -> restaurantRepository.getByNameAndZipCode("Unknown",42135).get());

    }
}
