package test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import apress.UserRegistrationSystem.Rest.UserRegistrationRestController;
import apress.UserRegistrationSystem.UserRegistrationSystemApplication;
import apress.UserRegistrationSystem.dto.UserDTO;
import apress.UserRegistrationSystem.repository.UserJpaRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserRegistrationSystemApplication.class,
        webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserRegistrationRestControllerTest {

    @Spy
    private UserRegistrationRestController userRegistrationRestController;

    @Mock
    private UserJpaRepository userJpaRepository;

    @Before
    public void setup() {
        userRegistrationRestController = new UserRegistrationRestController();
        ReflectionTestUtils.setField(userRegistrationRestController,
                "userJpaRepository", userJpaRepository);
    }

    @Test
    public void testListAllUsers() {

        List<UserDTO> userList = new ArrayList<UserDTO>();
        userList.add(new UserDTO());

        when(this.userJpaRepository.findAll()).thenReturn(userList);

        ResponseEntity<List<UserDTO>> responseEntity = this.userRegistrationRestController.listAllUsers();

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(1, responseEntity.getBody().size());
    }

    @After
    public void teardown() {
        userRegistrationRestController = null;
    }
}