package microteam.configuration;

import microteam.repository.JDBCCourse;
import microteam.services.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component//Step 8
public class JDBCCommandLineRunner implements CommandLineRunner {

    //Step 12.1
    @Autowired//Step 9
    private JDBCCourse repository;

    @Override
    public void run(String... args) throws Exception {
        repository.insert(new Course(5, "Java", "Learn Java","Oracle"));
        repository.deleteByID(1);
        System.out.println(repository.selectByID(5));
    }
}
