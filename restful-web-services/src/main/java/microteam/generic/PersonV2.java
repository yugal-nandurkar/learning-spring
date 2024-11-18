package microteam.generic;

// Step 31
public class PersonV2 {
    private Name name; // Name: Step 32

    public PersonV2(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PersonV2{" +
                "name=" + name +
                '}';
    }
}
