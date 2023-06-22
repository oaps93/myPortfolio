package com.dinerReview.dinerAPI.controller;

import com.dinerReview.dinerAPI.model.Restaurant;
import com.dinerReview.dinerAPI.model.User;
import com.dinerReview.dinerAPI.repository.DiningReviewRepository;
import com.dinerReview.dinerAPI.repository.RestaurantRepository;
import com.dinerReview.dinerAPI.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.util.Arrays;

import java.util.List;
import java.util.regex.Matcher;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@ContextConfiguration
//@WebAppConfiguration
@WebMvcTest(DinerReviewController.class)
@RunWith(SpringRunner.class)
public class DinerReviewControllerTest {

    @Autowired
    //private WebApplicationContext ctx;
    private MockMvc mockMvc;

    @MockBean
    private RestaurantRepository restaurantRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private DiningReviewRepository diningReviewRepository;

   // @Before
   // public void setUp(){
   //     this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
   // }

    @Test
    public void getAllRestaurantsTest() throws Exception{
        Restaurant restaurantA = new Restaurant();
        restaurantA.setName("Vips");
        restaurantA.setId(1l);

        Restaurant restaurantB = new Restaurant();
        restaurantB.setName("Sanborns");
        restaurantB.setId(2l);

        List<Restaurant> restaurants = Arrays.asList(restaurantA,restaurantB);

        Iterable<Restaurant> restaurantsIt = restaurants;
        ResponseEntity<Iterable<Restaurant>> restaurantResponse =
                new ResponseEntity<>(restaurantsIt, HttpStatus.OK);


        when(restaurantRepository.findAll()).thenReturn(restaurantsIt);

        mockMvc.perform(get("/diner/restaurants")
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].name").value("Sanborns"))
                .andExpect(jsonPath("$").isNotEmpty());
        }

    @Test
    public void createRestaurantTest() throws Exception{
        Restaurant restaurantA = new Restaurant();
        restaurantA.setName("Vips");
        restaurantA.setId(1l);



        mockMvc.perform(post("/diner/restaurant")
                        .content(asJsonString(restaurantA))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Test
    public void getAllUserTest() throws Exception{
        User user = new User();
        user.setName("Alfonso");
        user.setId(1l);
        user.setZipCode(44270);

        List<User> users = Arrays.asList(user);

        Iterable<User> userIt = users;

        when(userRepository.findAll()).thenReturn(userIt);

        mockMvc.perform(get("/diner/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Alfonso"))
                .andExpect(jsonPath("$[1]").doesNotExist())
                .andExpect(jsonPath("$").isNotEmpty());
    }
}






