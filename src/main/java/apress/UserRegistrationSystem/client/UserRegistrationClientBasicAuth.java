package main.java.apress.UserRegistrationSystem.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

public class UserRegistrationClientBasicAuth {

    private static final  String uName ="user";
    private static final String uPass="123";

    private static final  String USER_REGISTRATION_BASE_URL = "http://localhost:8080/api/user";

    private static RestTemplate restTemplate = new RestTemplate();

    public void  deleteUserByID(Long userId){

        String userCredential = uName+":"+uPass;

        byte[] base64UserCredentialData = Base64.getEncoder().encode(userCredential.getBytes());

        HttpHeaders authenticationHeaders = new HttpHeaders();

        authenticationHeaders.set("Authorization", "Basic "+ new String(base64UserCredentialData));

        HttpEntity<Void> httpEntity = new HttpEntity<Void>(authenticationHeaders);

        restTemplate.exchange(USER_REGISTRATION_BASE_URL + "/{id}", HttpMethod.DELETE, httpEntity, Void.class, userId);
    }

    public static void main(String[] args){
        UserRegistrationClientBasicAuth userRegistrationClientBasicAuth = new UserRegistrationClientBasicAuth();

        userRegistrationClientBasicAuth.deleteUserByID(46L);
    }

}
