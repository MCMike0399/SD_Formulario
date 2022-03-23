package mx.itam.packages.serialization;

import java.io.Serializable;

/**
 * @author Octavio Gutierrez
 */
public class Person implements Serializable { // Pone los bits necesarios para enviarlo

    private String name;
    private String place;
    private int year;

    public Person(String name, String place, int year) {
        this.name = name;
        this.place = place;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

    public int getYear() {
        return year;
    }

}
