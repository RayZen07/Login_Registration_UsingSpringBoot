package practice.UserLoginReg.Service;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import practice.UserLoginReg.Dto.LoginDto;
import practice.UserLoginReg.Dto.UserRegDto;
import practice.UserLoginReg.Entity.Role;
import practice.UserLoginReg.Entity.User;
import practice.UserLoginReg.Repository.UserRepo;

import java.io.Console;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class UserRegServiceImpl implements UserRegService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(UserRegDto userRegDto) {


        User user = new User(userRegDto.getFirstname(),
                userRegDto.getLastname(),
                userRegDto.getEmail(),
                userRegDto.getPassword(),
                Arrays.asList(new Role("ROLE_USER")));
        return userRepo.save(user);
    }

    @Override
    public LoginMessage login(LoginDto loginDto) {
        String msg = "";
        User user = userRepo.findByEmail(loginDto.getEmail());
        if (user != null) {
            String password = loginDto.getPassword();
            String pass = user.getPassword();
            Boolean isPwdRight = password.matches(pass);
            if (isPwdRight) {
                Optional<User> user1 = userRepo.findByEmailAndPassword(loginDto.getEmail(), pass);
                if (user1.isPresent()) {
                    return new LoginMessage("Login Success", true);
                } else {
                    return new LoginMessage("Login Failed", false);
                }

            } else {
                return new LoginMessage("Password Do not match", false);
            }
        } else {
            return new LoginMessage("Email Do Not Exist", false);
        }

    }



    @Override
    public List<User> usersList() {
        return userRepo.findAll();
    }

    public List<String> usersNames()
    {
        return userRepo.names();
    }


}
