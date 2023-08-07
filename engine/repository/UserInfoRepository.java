package engine.repository;

import engine.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmailIgnoreCase(String email);

}
