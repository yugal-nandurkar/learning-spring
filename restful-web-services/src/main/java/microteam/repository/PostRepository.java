package microteam.repository;

import microteam.generic.Post;
import org.springframework.data.jpa.repository.JpaRepository;

// Step 43.1
public interface PostRepository extends JpaRepository<Post, Integer> {
// UserJPAResource: Step 43.2
}
