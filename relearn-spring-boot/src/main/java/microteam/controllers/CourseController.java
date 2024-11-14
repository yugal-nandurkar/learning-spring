package microteam.controllers;

import microteam.services.Course;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@RestController //Step 1
public class CourseController {
    @RequestMapping("/courses") //Step 3
    public List<Course> retrieveAllCourses(){
        return Arrays.asList(
                new Course(1,"Learn AWS", "Amazon Web Services", "Amazon"),
                new Course(2,"Learn DevOps", "Developer Operations", "Github"),
                new Course(3,"Learn Azure", "Microsoft Azure", "Microsoft"),
                new Course(4,"Learn Figma", "Design Figma", "Designer")
        );
    }
}
