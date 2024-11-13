package microteam.applications.concepts;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//@Component //Step 34
@Named
class BusinessService {

    //@Autowired
    @Inject
    public void setDataService(BDataService bdataService) {
        System.out.println("Performing Setter Injection");
        this.bdataService = bdataService;
    }

    public BDataService getBDataService() {
        return bdataService;
    }

    BDataService bdataService;
}

//@Component
@Named
class BDataService {

}

@Configuration
@ComponentScan("microteam.applications.concepts")
public class JakartaCDI {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext
                (JakartaCDI.class);
        Arrays.stream(context.getBeanDefinitionNames())
                .forEach(System.out::println);
        System.out.println(
                context.getBean(BusinessService.class).getBDataService());
    }
}

