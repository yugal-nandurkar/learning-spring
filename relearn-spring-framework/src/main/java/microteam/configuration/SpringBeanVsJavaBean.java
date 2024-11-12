package microteam.configuration;

import java.io.Serializable;

class Pojo { //Plain Old Java Object
    private String text;
    private int number;

    @Override
    public String toString() {
        return "Pojo{" +
                "text='" + text + '\'' +
                ", number=" + number +
                '}';
    }
}

class JavaBean implements Serializable { //(Constraint 3) //Enterprise Java Beans
    //Constraint 1 - public no-arg constructor is implicit
    private String text;
    private int number;

    //Constraint 2 - Getters & Setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

public class SpringBeanVsJavaBean {
    public static void main(String[] args) {
        Pojo pojo = new Pojo();
        System.out.println(pojo);
    }
}