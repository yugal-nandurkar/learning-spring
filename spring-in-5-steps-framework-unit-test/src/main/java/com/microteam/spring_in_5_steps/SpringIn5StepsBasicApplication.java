package com.microteam.spring_in_5_steps;

import com.microteam.spring_in_5_steps.basic.BinarySearchImpl;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan //("com.microteam.spring_in_5_steps")
public class SpringIn5StepsBasicApplication {

	// What are the beans?
	//What are the dependencies of a bean?
	//Where to search for beans? -> No Need

	public static void main(String[] args) {
		// BinarySearchImpl binarySearch = new BinarySearchImpl(new QuickSortAlgorithm());
		// Application Context

		// com.microteam.spring_in_5_steps.BubbleSortAlgorithm@37374a5e
		// com.microteam.spring_in_5_steps.QuickSortAlgorithm@42eca56e

		try (AnnotationConfigApplicationContext applicationContext =
					 new AnnotationConfigApplicationContext(
							 SpringIn5StepsBasicApplication.class)) {

			BinarySearchImpl binarySearch =
					applicationContext.getBean(BinarySearchImpl.class);

			BinarySearchImpl binarySearch1 =
					applicationContext.getBean(BinarySearchImpl.class);

			System.out.println(binarySearch);
			System.out.println(binarySearch1);

			int result = binarySearch.binarySearch(new int[]{12, 4, 6}, 3);
			System.out.println(result);
			applicationContext.close();
			// com.microteam.spring_in_5_steps.BubbleSortAlgorithm@4690f583
			// 3
		}
	}
}
