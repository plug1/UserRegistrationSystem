package main.java.apress.UserRegistrationSystem.repository;

import apress.UserRegistrationSystem.dto.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoJpaRepository extends JpaRepository<UserInfo, Long> {
    public UserInfo findByUserName(String name);
}
