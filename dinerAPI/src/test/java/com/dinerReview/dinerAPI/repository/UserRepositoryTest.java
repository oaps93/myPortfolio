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
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void userRepository_Save_ReturnSavedUserTest(){

        User user = User.builder()
                .name("Juan")
                .build();

        User savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }
    @Test
    public void userRepository_Save_NotValidUserToSaveTest(){
        User user = User.builder()
                .city("Leon")
                .state("Guanajuato")
                .build();

       assertThrows(Exception.class, () -> userRepository.save(user));
    }

    @Test
    public void userRepository_GetAll_AllUsersTest(){

        Iterable<User> allUsers = userRepository.findAll();

        List<User> allUserList = new LinkedList<>();
        allUsers.forEach( u -> allUserList.add(u));

        assertNotNull(allUsers);
        assertEquals(allUserList.size(),4);
    }

    @Test
    public void setUserRepository_GetById_FindByIdTest(){

        User user = User.builder()
                .name("Josue")
                .build();

        User userSaved = userRepository.save(user);

        User userFound = userRepository.findById(userSaved.getId()).get();

        assertNotNull(userFound);
        assertEquals(userSaved,userFound);
    }

    @Test
    public void setUserRepository_Get_notFoundByIdTest(){

        User user = User.builder()
                .name("Josue")
                .build();

        User userSaved = userRepository.save(user);
        Iterable<User> allUsers = userRepository.findAll();

        //allUsers.forEach( u -> System.out.println(u.getId()));

        assertThrows(Exception.class, () -> userRepository.findById(999l).get());

    }

    @Test
    public void setUserRepository_GetByName_FindByIdTest(){

        User user = User.builder()
                .name("Lola")
                .build();

        User userSaved = userRepository.save(user);

        User userFound = userRepository.getByName(userSaved.getName()).get();

        assertEquals(userSaved.getId(), userFound.getId());

    }

    @Test
    public void setUserRepository_GetByName_NotFoundByNameTest(){

        User user = User.builder()
                .name("Lola")
                .build();

        User userSaved = userRepository.save(user);

        assertThrows(NoSuchElementException.class, () -> userRepository.getByName("Yessenia").get());

    }


}
