package com.microteam.spring_in_5_steps.cdi;

import org.springframework.stereotype.Component;

import javax.inject.Named;

@Named
public class SomeCDIDAO {
    public int[] getData(){
        return new int[]{5, 89, 100};
    }
}
