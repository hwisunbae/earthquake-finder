package com.example.lab7;

public class Person {
    private String name;
    private String dept;
    private String year;

    Person() {

    }

    Person(String name, String dept, String year) {
        this.name = name;
        this.dept = dept;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public String getYear() {
        return year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
