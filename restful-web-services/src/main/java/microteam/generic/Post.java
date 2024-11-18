package microteam.generic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity //Step 41.1
public class Post {
// User: Step 41.2
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 10, max = 50) // Step 44.1
    private String description;
// UserJPAResource: Step 42
    @ManyToOne(fetch = FetchType.LAZY) // Step 41.3
    @JsonIgnore
    private User user;

    public Post(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Post() {

    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

    // Step 44.2
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // User: Step 44.3
}
