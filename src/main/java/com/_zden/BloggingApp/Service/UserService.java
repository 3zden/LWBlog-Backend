package com._zden.BloggingApp.Service;

import com._zden.BloggingApp.DTOs.CreateUserDTO;
import com._zden.BloggingApp.DTOs.LoginUser;
import com._zden.BloggingApp.DTOs.UserDTO;
import com._zden.BloggingApp.Entities.User;
import com._zden.BloggingApp.Jwt.JwtService;
import com._zden.BloggingApp.Repositories.UserRepo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    authTest auth = new authTest();
    UserRepo userRepo;
    JwtService jwtService;
    public ResponseEntity<User> createUser(CreateUserDTO user) throws Exception {
        User temp = new User(user.email(),auth.hash(user.password()),user.firstName(),user.lastName());
        userRepo.save(temp);
        return ResponseEntity.status(HttpStatus.CREATED).body(temp);
    }

    public ResponseEntity<?> loginUser(LoginUser user) throws Exception {
        if (userRepo.existsUserByEmail(user.email())){
            User temp = userRepo.findByEmail(user.email());
            if (Objects.equals(temp.getPassword(), auth.hash(user.password()))){
                System.out.println("the user is authentificated!!!");
                return ResponseEntity.ok(jwtService.generateJwtToken(temp.getEmail(), temp.getPassword()));
            }else {return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();}
        }
        else {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();

    }
    public UserService(UserRepo userRepo, JwtService jwtService){
        this.userRepo = userRepo;
        this.jwtService = jwtService;
    }
}
