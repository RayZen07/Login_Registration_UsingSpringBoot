package practice.UserLoginReg.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import practice.UserLoginReg.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByEmailAndPassword(String email,String password);
    User findByEmail(String email);

    @Query("SELECT firstName FROM User")
    List<String> names();

    Boolean existsByEmail(String email);


}
