package main.java.apress.UserRegistrationSystem.client;

import apress.UserRegistrationSystem.dto.UserDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UserRegistrationClient {

    private static RestTemplate restTemplate = new RestTemplate();
    private static final String USER_REGISTRATION_BASE_URL = "http://localhost:8080/api/user";

    public static void main(String[] args){
        UserRegistrationClient userRegistrationClient = new UserRegistrationClient();



        ResponseEntity<UserDTO> responseEntity =
                userRegistrationClient.getUserByIdUsingExchangeAPI(1L);
        UserDTO user = responseEntity.getBody();
        System.out.println(user.getName());

    }

    public ResponseEntity<UserDTO> getUserByIdUsingExchangeAPI(final Long userId) {

        HttpEntity<UserDTO> httpEntity = new HttpEntity<UserDTO>(new UserDTO());

        return restTemplate.exchange(USER_REGISTRATION_BASE_URL + "/{id}",
                HttpMethod.GET, httpEntity, UserDTO.class,  userId);
    }

    public void deleteUser(final Long userId) {
        restTemplate.delete(
                USER_REGISTRATION_BASE_URL + "/{id}",
                userId);
    }

    public UserDTO getUserById(final Long userId) {
        return restTemplate.getForObject(USER_REGISTRATION_BASE_URL + "/{id}", UserDTO.class, userId);
    }

    public UserDTO createUser(final UserDTO user) {
        return restTemplate.postForObject(
                USER_REGISTRATION_BASE_URL,
                user,
                UserDTO.class);
    }

    public void updateUser(final Long userId, final UserDTO user) {
        restTemplate.put(
                USER_REGISTRATION_BASE_URL + "/{id}",
                user,
                userId);
    }
}


