package com.microteam.spring_in_5_steps.cdi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class SomeCDIBusiness {

    @Inject
    SomeCDIDAO someCDIDAO;

    public SomeCDIDAO getSomeCDIDAO() {
        return someCDIDAO;
    }

    public void setSomeCDIDAO(SomeCDIDAO someCDIDAO) {
        this.someCDIDAO = someCDIDAO;
    }

     public int findGreatest(){
        int greatest = Integer.MIN_VALUE;
        int[] data = someCDIDAO.getData();
        for (int value: data) {
            if(value > greatest){
                greatest = value;
            }
        }
        return greatest;
     }
}
