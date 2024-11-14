package microteam.repository;

import microteam.services.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SpringDataCourse extends JpaRepository<Course, Integer> {
    List<Course> findByAuthor(String author);
    List<Course> findByName(String name);
}
