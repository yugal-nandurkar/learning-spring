package com.microteam.spring_in_5_steps;

import com.microteam.spring_in_5_steps.cdi.SomeCDIBusiness;
import com.microteam.spring_in_5_steps.scope.PersonDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringIn5StepsCDIApplication {

	public static void main(String[] args) {

		Logger LOGGER = LoggerFactory.getLogger(SpringIn5StepsCDIApplication.class);

		ApplicationContext applicationContext = SpringApplication.run(SpringIn5StepsCDIApplication.class, args);
		SomeCDIBusiness business = applicationContext.getBean(SomeCDIBusiness.class);

		LOGGER.info("{} dao-{}", business, business.getSomeCDIDAO());

	}

}
