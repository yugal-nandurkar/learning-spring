package microteam.springboot.basics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class BooksController {

    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return Arrays.asList(new Book(1, "Mastering Spring 6.0", "Naughty Nuts"));
        }
}
