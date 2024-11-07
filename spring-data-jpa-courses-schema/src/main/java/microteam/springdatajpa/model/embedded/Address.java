package microteam.springdatajpa.model.embedded;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {

    private String houseNumber;
    private String streetName;
    private String city;
    private String state;
    private String zip;
}
