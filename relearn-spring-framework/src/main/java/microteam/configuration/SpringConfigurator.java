package microteam.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

record Person(String name, int age) {};
;

@Configuration
public class SpringConfigurator {
    //Step 4 Configured Bean (Java Object Managed by Spring)
    @Bean
    public String name() {
        return "Spring Configurator";
    }

    @Bean
    public int age() {
        return 20;
    }

    @Bean
    public Person first_person() {
        return new Person("John Doe", 18);
    }

    @Bean
    @Primary
    public Person second_person() {
        return new Person("Jane Doe", 19);
    }

    @Bean(name = "european_address")
    @Primary // Step 6 (Qualifying Bean Avoiding Multiplicity)
    public Address address_eu() {
        return new Address("Baker Street", "London", "England", "13B");
    }

    @Bean(name = "american_address")
    @Qualifier("qualifier_address") // Step 7 Skip on Default
    public Address address_us() {
        return new Address("Down Town", "New York", "Eastern States", "21A");
    }

    @Bean
    public Person personMethodCall(){
        return new Person(name(), age());
    }

    @Bean
    public Person personParameters(String name, int age) {
        return new Person(name, age);
    }
}
