package Evrything.todoManagement.repository;

import Evrything.todoManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);
    Boolean existByEmail(String email);
    Optional<User> findByUserNamOrEmail(String username,String email);
}
