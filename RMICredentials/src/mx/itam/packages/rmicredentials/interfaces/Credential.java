package mx.itam.packages.rmicredentials.interfaces;

import java.io.Serializable;

public class Credential implements Serializable { // Es Serializable pues enviamos el objeto
    private String name;
    private String location;
    private int year;
    private String pw;

    public Credential() {
        this.name = "";
        this.location = "";
        this.year = 1900;
        this.pw = "";
    }

    public Credential(String aName, String location, int year, String pw) {
        this.name = name;
        this.location = location;
        this.year = year;
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}