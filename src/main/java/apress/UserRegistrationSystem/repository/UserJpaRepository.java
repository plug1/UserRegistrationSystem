package main.java.apress.UserRegistrationSystem.repository;

import apress.UserRegistrationSystem.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;



public interface UserJpaRepository extends JpaRepository<UserDTO, Long> {




    UserDTO findByName(String name);
}
