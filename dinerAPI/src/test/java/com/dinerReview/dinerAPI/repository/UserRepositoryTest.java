package com.dinerReview.dinerAPI.repository;

import com.dinerReview.dinerAPI.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void userRepository_Save_returnSavedUserTest(){

        User user = User.builder()
                .name("Juan")
                .build();

        User savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }
    @Test
    public void userRepository_Save_notValidUserToSaveTest(){
        User user = User.builder()
                .city("Leon")
                .state("Guanajuato")
                .build();

       assertThrows(Exception.class, () -> userRepository.save(user));
    }

    @Test
    public void setUserRepository_Get_allUsersTest(){

        Iterable<User> allUsers = userRepository.findAll();

        List<User> allUserList = new LinkedList<>();
        allUsers.forEach( u -> allUserList.add(u));

        assertNotNull(allUsers);
        assertEquals(allUserList.size(),4);
    }

}
