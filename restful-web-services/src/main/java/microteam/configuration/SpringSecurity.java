package microteam.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Step 45
public class SpringSecurity {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                authorizeRequests -> authorizeRequests.anyRequest().authenticated());
        http.httpBasic(Customizer.withDefaults());
        http.csrf().disable();
        return http.build();
    }
}
