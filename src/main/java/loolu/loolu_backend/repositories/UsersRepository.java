package loolu.loolu_backend.repositories;

import loolu.loolu_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long>{

   // boolean existsById(Long id);

    boolean existsByEmail(String email);

   // Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);
}
