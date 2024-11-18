package microteam.generic;

import jakarta.validation.Valid;
import microteam.exceptions.UserNotFoundException;
import microteam.repository.PostRepository;
import microteam.repository.UserRepository;
import microteam.services.UserDAO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController // Step 40.1
public class UserJPAResource {

    private UserDAO service;

    private UserRepository repository;

    // Step 43.2
    private PostRepository postRepository;

    public UserJPAResource(UserDAO service, UserRepository repository, PostRepository postRepository)
    {
        this.service = service;
        this.repository = repository;
        this.postRepository = postRepository;
    }

    @GetMapping("/jpa/users") // Step 40.2
    public List<User> retrieveUsers() {

        return repository.findAll();
    }

    @GetMapping("/jpa/users/{id}") // Step 40.3
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("id:" + id);
        EntityModel<User> model = EntityModel.of(user.get());
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveUsers());
        model.add(linkTo.withRel("all-users"));
        return model;
    }

    @PostMapping("/jpa/users") //Step 40.5
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = repository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    // Step 40.4
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {

        repository.deleteById(id);
    }
    // Post: Step 41

    // Step 42
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("id:" + id);
        return user.get().getPosts();
    }

    // PostRepository: Step 43

    @PostMapping("/jpa/users/{id}/posts") // Step 43.3
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("id:" + id);
        post.setUser(user.get());
        Post savedPost = postRepository.save(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPost.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    // Post: Step 44
}
