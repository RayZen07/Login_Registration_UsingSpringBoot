package practice.UserLoginReg.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.UserLoginReg.Dto.LoginDto;
import practice.UserLoginReg.Dto.UserRegDto;
import practice.UserLoginReg.Entity.User;
import practice.UserLoginReg.Repository.UserRepo;
import practice.UserLoginReg.Service.LoginMessage;
import practice.UserLoginReg.Service.UserRegServiceImpl;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserRegController {
    @Autowired
    private UserRegServiceImpl userRegService;
    @Autowired
    private UserRepo userRepo;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRegDto userRegDto)
    {
        if (userRepo.existsByEmail(userRegDto.getEmail()))
        {
            throw new RuntimeException("Email Already Exist");
        }
        User registerUser=userRegService.saveUser(userRegDto);
        return new ResponseEntity<>(registerUser, HttpStatus.CREATED);

    }
    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto)
    {
        LoginMessage loginMessage=userRegService.login(loginDto);
        return  ResponseEntity.ok(loginMessage);
    }
    @GetMapping(path = "/usersNames")
    public ResponseEntity<List<String>> getNames()
    {
       List<String> strings=userRegService.usersNames();
       return new ResponseEntity<>(strings,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<User>> getAll()
    {
     List<User> users=userRegService.usersList();
     return new ResponseEntity<>(users,HttpStatus.OK);
    }
}
