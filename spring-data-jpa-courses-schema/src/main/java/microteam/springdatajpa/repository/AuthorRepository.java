package microteam.springdatajpa.repository;

import microteam.springdatajpa.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer>, JpaSpecificationExecutor<Author> {

    List<Author> findByNamedQuery(@Param("age") int age);

    @Modifying
    @Transactional
    void updateByNamedQuery(@Param("age") int age);
    // Method to update an author's age by their ID
    @Modifying  // This annotation tells Spring Data JPA that this query is a modifying operation
    @Transactional  // Ensures the query is executed within a transaction
    @Query("UPDATE Author a SET a.age = :age WHERE a.id = :id")
    int updateAuthor(int age, int id);

    // Additional query methods for finding authors by first name
    List<Author> findAllByFirstName(String firstName);
    List<Author> findAllByFirstNameIgnoreCase(String firstName);
    List<Author> findAllByFirstNameContainingIgnoreCase(String firstName);
    List<Author> findAllByFirstNameStartsWithIgnoreCase(String firstName);
    List<Author> findAllByFirstNameEndsWithIgnoreCase(String firstName);
    List<Author> findAllByFirstNameInIgnoreCase(List<String> firstNames);
}
