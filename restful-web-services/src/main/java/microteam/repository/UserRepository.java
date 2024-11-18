package microteam.repository;

import microteam.generic.User;
import org.springframework.data.jpa.repository.JpaRepository;
 // Step 39
public interface UserRepository extends JpaRepository<User, Integer> {
 // UserJPAResource: Step 40
}
