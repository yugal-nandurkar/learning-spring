package microteam.springdatajpa;

import com.github.javafaker.Faker;
import microteam.springdatajpa.model.Author;
import microteam.springdatajpa.model.Video;
import microteam.springdatajpa.repository.AuthorRepository;
import microteam.springdatajpa.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OldFakerSpringDataJpaApplication implements CommandLineRunner {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private VideoRepository videoRepository;

    public static void main(String[] args) {
        SpringApplication.run(OldFakerSpringDataJpaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Faker faker = new Faker();

        /*// Saving 50 authors
        for (int i = 0; i < 50; i++) {
            Author author = Author.builder() // Builder for Author
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .age(faker.number().numberBetween(1, 100))
                    .email(faker.internet().emailAddress())
                    .build();
            authorRepository.save(author); // Saving author instance
        }*/

        // Saving a video
        Video video = Video.builder()
                .name("abc")
                .length(5)
                .build();
        videoRepository.save(video); // Saving video instance*/
        var author = Author.builder()
                .id(51)
                .firstName("John")
                .lastName("Doe")
                .age(31)
                .email("john@doe.com")
                .build();
        authorRepository.updateAuthor(22,52);
    }
}