package microteam.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import microteam.services.Course;
import org.springframework.stereotype.Repository;

@Repository//Step 11
@Transactional//Step 12.2
public class JPACourse {
    @PersistenceContext
    private EntityManager em;

    public void insert(Course course) {
        em.merge(course);
    }

    public Course selectByID(int id) {
        return em.find(Course.class, id);
    }

    public void deleteByID(int id) {
        Course course = em.find(Course.class, id);
        em.remove(course);
    }
}
