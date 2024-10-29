package com.microteam.spring_in_5_steps;

import com.microteam.spring_in_5_steps.scope.PersonDAO;
import componentscan.ComponentDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan("componentscan")
public class SpringIn5StepsComponentScanApplication {

	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				SpringIn5StepsComponentScanApplication.class)) {
			ComponentDAO componentDAO = applicationContext.getBean(ComponentDAO.class);

			LOGGER.info("{}", componentDAO);
		}
	}

}
