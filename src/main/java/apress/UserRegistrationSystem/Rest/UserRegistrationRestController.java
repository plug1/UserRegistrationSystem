package main.java.apress.UserRegistrationSystem.Rest;

import apress.UserRegistrationSystem.Exceptions.CustomErrorType;
import apress.UserRegistrationSystem.dto.UserDTO;
import apress.UserRegistrationSystem.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserRegistrationRestController {



    private UserJpaRepository userJpaRepository;

    @Autowired
    public void  setUserJpaRepository(UserJpaRepository userJpaRepository){
        this.userJpaRepository=userJpaRepository;
    }




    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> listAllUsers(){

       // BcryptGenerate bcryptGenerate = new BcryptGenerate();
       // System.out.println(bcryptGenerate.getHash("DUPA"));

        List<UserDTO> users = userJpaRepository.findAll();
        if(users.isEmpty())
            return new ResponseEntity<List<UserDTO>>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") final Long id) {
        Optional<UserDTO> user = userJpaRepository.findById(id);
        if (!user.isPresent()){
            return new ResponseEntity<UserDTO>(new CustomErrorType("no user definide"),
                    HttpStatus.NOT_FOUND );
        }
        return new ResponseEntity<UserDTO>(user.get(), HttpStatus.OK);

    }

    @PostMapping(value = "/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody final UserDTO user){
        if (userJpaRepository.findByName(user.getName())!=null){
            return new ResponseEntity<UserDTO>(
                    new CustomErrorType("User: "+user.getName()+" aleredy exists"),HttpStatus.CONFLICT);
        }
        userJpaRepository.save(user);
        return  new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") final Long id, @RequestBody UserDTO user){

        Optional<UserDTO> currentUser = userJpaRepository.findById(id);
        if(!currentUser.isPresent()){
            return new ResponseEntity<UserDTO>(new CustomErrorType("User not exists"), HttpStatus.NOT_FOUND);
        }

        currentUser.get().setName(user.getName());
        currentUser.get().setAddress(user.getAddress());
        currentUser.get().setEmail(user.getEmail());

        userJpaRepository.saveAndFlush(currentUser.get());

        return new ResponseEntity<UserDTO>(currentUser.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") final Long id){
        Optional<UserDTO> user = userJpaRepository.findById(id);
        if(!user.isPresent()){
            return new ResponseEntity<UserDTO>(new CustomErrorType("Unable to delete, user not exists"), HttpStatus.NOT_FOUND);
        }
        userJpaRepository.deleteById(id);
        return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
    }



}
