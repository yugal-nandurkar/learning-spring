package com.microteam.spring_in_5_steps;

import com.microteam.spring_in_5_steps.basic.BinarySearchImpl;
import com.microteam.spring_in_5_steps.properties.SomeExternalService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan //("com.microteam.spring_in_5_steps")
//load app.properties
@PropertySource("classpath:app.properties")
public class SpringIn5StepsPropertiesApplication {

	// What are the beans?
	//What are the dependencies of a bean?
	//Where to search for beans? -> No Need

	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext applicationContext =
					 new AnnotationConfigApplicationContext(
							 SpringIn5StepsPropertiesApplication.class)) {

			SomeExternalService service =
					applicationContext.getBean(SomeExternalService.class);

			System.out.println(service.returnServiceURL());
		}
	}
}
