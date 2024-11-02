package com.microteam.spring_in_5_steps.basic;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Load the context
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = "/testContext.xml")
public class BinarySearchXMLConfigurationTest {

    //Get this bean from the context
    @Autowired
    BinarySearchImpl binarySearch;

    @Test
    public void testBasicScenario() {
        // call method on binary search
        int actualResult = binarySearch.binarySearch(new int[] {}, 5);

        // check if the value is correct
        assertEquals(3, actualResult);



    }
}
