package practice.UserLoginReg.Service;

import practice.UserLoginReg.Dto.LoginDto;
import practice.UserLoginReg.Dto.UserRegDto;
import practice.UserLoginReg.Entity.User;

import java.util.List;

public interface UserRegService {

    User saveUser(UserRegDto userRegDto);

    LoginMessage login(LoginDto loginDto);


    List<User> usersList();

}
