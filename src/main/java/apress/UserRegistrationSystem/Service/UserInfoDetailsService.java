package main.java.apress.UserRegistrationSystem.Service;

import apress.UserRegistrationSystem.dto.UserInfo;
import apress.UserRegistrationSystem.repository.UserInfoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserInfoDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoJpaRepository userInfoJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userInfoJpaRepository.findByUserName(username);
        if (user==null){
            throw new UsernameNotFoundException("User not found with usernamne: "+ username);
        }
        return new User(user.getUserName(), user.getPassword(),getAuthirities(user));
    }

    private Collection<GrantedAuthority> getAuthirities(UserInfo user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities = AuthorityUtils.createAuthorityList(user.getRole());
        return authorities;
    }


}
