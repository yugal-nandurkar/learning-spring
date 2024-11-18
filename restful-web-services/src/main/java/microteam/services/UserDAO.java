package microteam.services;

import microteam.generic.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component // Step 9
public class UserDAO {

    private static List<User> users = new ArrayList<>();

    // Step 16.2
    private static Integer usersCount = 0;

    static {
        // Step 16.3 ++usersCount
        users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount, "Bob", LocalDate.now().minusYears(25)));
        users.add(new User(++usersCount, "Charlie", LocalDate.now().minusYears(20)));
    }
    public List<User> getUsers() {
        return users;
    }

    // UserResource: Step 10

    // Step 13
    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        // Step 17.2 .orElse(null)
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    // UserResource: Step 14

    // Step 23
    public void deleteById(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }

    // UserResource: Step 24

    // Step 16.1
    public User save(User user) {
        user.setId(++usersCount); // Step 16.4
        users.add(user);
        return user;
    }

    // UserResource: Step 17
}
