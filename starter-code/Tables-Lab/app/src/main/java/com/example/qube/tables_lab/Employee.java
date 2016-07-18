package com.example.qube.tables_lab;

/**
 * Created by Qube on 7/17/16.
 */

// This class is the Employee Table
public class Employee {
    private String ssn;
    private String firstName;
    private String lastName;
    private int yearOfBirth;
    private String city;

    // Default Constructor
    public Employee() {
    }

    // Constructor
    public Employee(String ssn, String firstName, String lastName, int yearOfBirth, String city) {
        Employee.this.ssn = ssn;
        Employee.this.firstName = firstName;
        Employee.this.lastName = lastName;
        Employee.this.yearOfBirth = yearOfBirth;
        Employee.this.city = city;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
