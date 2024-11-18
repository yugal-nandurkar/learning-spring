package microteam.generic;

import jakarta.validation.Valid;
import microteam.exceptions.UserNotFoundException;
import microteam.services.UserDAO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
// Step 33.3
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController // Step 10
public class UserResource {

    private UserDAO service;

    public UserResource(UserDAO service) {
        this.service = service;
    }

    @GetMapping("/users") // Step 11
    public List<User> retrieveUsers() {

        return service.getUsers();
    }

    /*@GetMapping("/users/{id}") // Step 12
    public User retrieveUser(@PathVariable int id) {
        // UserDAO: Step 13
        User user = service.findOne(id); // Step 17.3
        // UserNotFoundException: Step 18
        if (user == null) throw new UserNotFoundException("id:" + id);
        {

        }
        return user;  // Step 14
    }*/

    @GetMapping("/users/{id}") // Step 33.1
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        // UserDAO: Step 13
        User user = service.findOne(id); // Step 17.3
        // UserNotFoundException: Step 18
        if (user == null)
            throw new UserNotFoundException("id:" + id);

        EntityModel<User> model = EntityModel.of(user); // Step 33.2
        // Step 33.4
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveUsers());
        // HATEOAS: Hypermedia as the Engine of Application State
        model.add(linkTo.withRel("all-users"));
        return model;  // Step 14
    }

    // User: Step 34

    @PostMapping("/users") //Step 15
    // Step 24 @Valid
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        // UserDAO: Step 16
        User savedUser = service.save(user); // Step 17.1
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    // User: Step 25

    // Step 22
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {

        service.deleteById(id);
    }

    // UserDAO: Step 23

}
