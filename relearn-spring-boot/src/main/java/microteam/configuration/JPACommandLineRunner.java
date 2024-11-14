package microteam.configuration;

import microteam.repository.JPACourse;
import microteam.services.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JPACommandLineRunner implements CommandLineRunner {

    //Step 13.2
    @Autowired
    private JPACourse repository;

    @Override
    public void run(String... args) throws Exception {
        repository.insert(new Course(6, "Python", "Learn Python","Real Python"));
        System.out.println(repository.selectByID(3));
    }
}
