package com.microteam.spring_in_5_steps.cdi;

import com.microteam.spring_in_5_steps.SpringIn5StepsBasicApplication;
import com.microteam.spring_in_5_steps.basic.BinarySearchImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Load the context
@RunWith(MockitoJUnitRunner.class)
//@ContextConfiguration (classes = SpringIn5StepsBasicApplication.class)
public class SomeCDIBusinessTest {

    //@Autowired
    //Inject Mock
    @InjectMocks
    SomeCDIBusiness business;

    //Create Mock
    @Mock
    SomeCDIDAO daoMock;

    @Test
    public void testBasicScenario() {

        // when daoMock.getData() method is called, return int[] {2, 4}
        Mockito.when(daoMock.getData()).thenReturn(new int[] {2, 4});

        // check if the value is correct
        assertEquals(4, business.findGreatest());
    }

    @Test
    public void testBasicScenario_NoValues() {

        Mockito.when(daoMock.getData()).thenReturn(new int[] {});

        // check if the value is correct
        assertEquals(Integer.MIN_VALUE, business.findGreatest());
    }

    @Test
    public void testBasicScenario_EqualElements() {

        Mockito.when(daoMock.getData()).thenReturn(new int[] {2 , 2});

        // check if the value is correct
        assertEquals(2, business.findGreatest());
    }
}
