package com.microteam.spring_in_5_steps;

import ch.qos.logback.classic.Logger;
import com.microteam.spring_in_5_steps.basic.BinarySearchImpl;
import com.microteam.spring_in_5_steps.xml.XMLPersonDAO;
import com.microteam.spring_in_5_steps.xml.XMLJdbcConnection;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringIn5StepsXMLContextApplication {

    private static Logger LOGGER = LoggerFactory.getLogger(SpringIn5StepsScopeApplication);

    public static void main(String[] args) {

        try (ClassPathXmlApplicationContext applicationContext =
                     new ClassPathXmlApplicationContext("applicationContext.xml") {
            LOGGER.info("Beans Loaded -> {}",(object)applicationContext.getBeanDefinitionNames());

            XMLPersonDAO personDao = applicationContext.getBean(XMLPersonDAO.class);

            System.out.println(personDao);
            System.out.println(personDao.getXMLJdbcConnection());

                     }
    }
}
