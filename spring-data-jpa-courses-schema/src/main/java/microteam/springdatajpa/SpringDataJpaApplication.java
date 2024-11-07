package microteam.springdatajpa;

import com.github.javafaker.Faker;
import microteam.springdatajpa.model.Author;
import microteam.springdatajpa.repository.AuthorRepository;
import microteam.springdatajpa.repository.VideoRepository;
import microteam.springdatajpa.specification.AuthorSpecification;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class SpringDataJpaApplication implements CommandLineRunner {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private VideoRepository videoRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

    @Override
    @Transactional  // Ensure this method is wrapped in a transaction
    public void run(String... args) {
        // Create a new author
        var author = Author.builder()
                .id(51)
                .firstName("John")
                .lastName("Doe")
                .age(31)
                .email("john@doe.com")
                .build();

        // Persist the new author (this could be save or update depending on the id)
        //authorRepository.save(author);

        // Call the update method with an example update for the author
        // Assuming the method updates an author's email, for example
        //authorRepository.updateAuthor(22, 52);  // Assuming this method exists in AuthorRepository

       /* authorRepository.findByNamedQuery(50)
                .forEach(System.out::println); */
       // authorRepository.updateByNamedQuery(12);
        Specification<Author> spec = Specification
                .where(AuthorSpecification.hasAge(31))
                .and(AuthorSpecification.firstNameContains("J"));
        authorRepository.findAll(spec).forEach(System.out::println);
    }
}
