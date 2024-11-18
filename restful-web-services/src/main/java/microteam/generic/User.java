package microteam.generic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

// Step 8
@Entity(name="user_details") // Step 38 user is reserved keyword in h2
// UserRepository: Step 39
public class User {
    public User() {
    }
    // UserDAO: Step 9
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 2, max = 20, message = "Name should have at least 2 characters" ) // Step 25 Validations - @Size @Past
    @JsonProperty("user_name") // Step 34.1
    // Filtering: Step 34.2
    private String name;
    @Past(message = "Birth date should be in the past")
    private LocalDate birthday;

    @OneToMany(mappedBy = "user")//Step 41.2
    @JsonIgnore
    private List<Post> posts;

    // Post: Step 41.3

    // CustomizedResponseEntityExceptionHandler: Step 26

    public User(Integer id, String name, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    //Step 44.3

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    // SpringSecurity: Step 45
}
