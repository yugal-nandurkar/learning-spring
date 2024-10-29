package com.microteam.spring_in_5_steps.basic;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BinarySearchImpl {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("quick")
    private SortAlgorithm sortAlgorithm;

    // public void setSortAlgorithm(SortAlgorithm sortAlgorithm) {
    //    this.sortAlgorithm = sortAlgorithm;
    //}

    // public BinarySearchImpl(SortAlgorithm sortAlgorithm) {
   //     super();
   //     this.sortAlgorithm = sortAlgorithm;
   // }

    // Sorting an Array, Searching it and Returning the Result
    public int binarySearch(int[] numbers, int numberToSearchFor) {
       // BubbleSortAlgorithm bubbleSortAlgorithm = new BubbleSortAlgorithm();
        int[] sortedNumbers = sortAlgorithm.sort(numbers);
        System.out.println(sortAlgorithm);
        // Implement sorting logic
        return 3;
    }

    @PostConstruct
    public void postConstruct() {
        logger.info("postConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        logger.info("preDestroy");
    }

}
