package test;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.nio.charset.Charset;

import apress.UserRegistrationSystem.Exceptions.CustomErrorType;
import apress.UserRegistrationSystem.Rest.UserRegistrationRestController;
import apress.UserRegistrationSystem.dto.UserDTO;
import apress.UserRegistrationSystem.repository.UserJpaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserRegistrationRestController.class)
@ContextConfiguration(classes = UserRegistrationSystemApplication.class)
public class UserRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserJpaRepository userJpaRepositoryMock;

    private MediaType contentType;

    private UserDTO user;


    @Before
    public void setup() {

        contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(),
                Charset.forName("utf8"));

        user = new UserDTO();
        user.setName("XX");
        user.setAddress("XX");
        user.setEmail("mk@g.com");

    }

    @Test
    public void shouldReturnSuccessMessage() throws Exception {

       // when(this.userJpaRepositoryMock.findById(1L)).thenReturn(users);

        this.mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("'.name", is("XX")))
                .andExpect(jsonPath("'.address",
                        is("XX")))
                .andExpect(jsonPath("'.email",
                        is("mk@g.com")))
                .andDo(print());
    }
}