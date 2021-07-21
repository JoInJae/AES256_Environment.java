package app.repository.part.user;

import app.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface User_Repository extends JpaRepository<User, Long> {

    Optional<User> findByUuid(String uuid);

}
