package microteam.controllers;


import microteam.generic.Name;
import microteam.generic.PersonV1;
import microteam.generic.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Step 29
public class VersioningPerson {
    @GetMapping("/v1/person")
    //http://localhost:8080/api/v1/person
    public PersonV1 getFirstVersionOfPerson(){
        return new PersonV1("Bob Charlie"); // PersonV1: Step 30
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson(){
        return new PersonV2(new Name("Bob", "Charlie")); // PersonV2: Step 31
    }

    @GetMapping(path="/person", params="version=1") // Step 32.1
    //http://localhost:8080/api/person?version=1
    public PersonV1 getFirstVersionOfPersonRequestParameter(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path="/person", params="version=2") // Step 32.2
    public PersonV2 getSecondVersionOfPersonRequestParameter(){
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    @GetMapping(path="/person/header", headers="X-API-VERSION=1") // Step 32.3
    //http://localhost:8080/api/person/header
    public PersonV1 getFirstVersionOfPersonRequestHeaders(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path="person/header", headers="X-API-VERSION=2") // Step 32.4
    public PersonV2 getSecondVersionOfPersonRequestHeaders(){
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    @GetMapping(path="/person/accept", produces="application/vnd.company.app-v1+json") // Step 32.5
    //http://localhost:8080/api/person/accept
    public PersonV1 getFirstVersionOfPersonAcceptHeader(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path="person/accept", headers="application/vnd.company.app-v2+json") // Step 32.6
    public PersonV2 getSecondVersionOfPersonAcceptHeader(){
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    // UserResource: Step 33
}
