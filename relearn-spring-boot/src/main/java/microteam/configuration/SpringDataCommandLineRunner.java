package microteam.configuration;

import microteam.repository.SpringDataCourse;
import microteam.services.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SpringDataCommandLineRunner implements CommandLineRunner {

    @Autowired
    private SpringDataCourse repository;

    @Override //Step 14
    public void run(String... args) throws Exception {
        repository.save(new Course(7, "C-Sharp", "Learn C#","Aspire"));
        System.out.println(repository.findById(4));
        System.out.println(repository.findAll());
        System.out.println(repository.count());
        System.out.println(repository.findByAuthor("Oracle"));
        System.out.println(repository.findByName("Learn AWS"));
    }
}
