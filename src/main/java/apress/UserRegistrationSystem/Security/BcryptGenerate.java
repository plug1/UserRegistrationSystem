package main.java.apress.UserRegistrationSystem.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;


public class BcryptGenerate {

    @Bean
    public String getHash(String password){
        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        return passwordHash;
    }
}
