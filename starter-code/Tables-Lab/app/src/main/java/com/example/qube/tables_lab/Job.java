package com.example.qube.tables_lab;

/**
 * Created by Qube on 7/17/16.
 */

// Job table
public class Job {
    private String ssn;
    private String company;
    private int salary;
    private int experience;

    // Default Constructor
    public Job() {
    }

    // Constructor
    public Job(String ssn, String company, int salary, int experience) {
        Job.this.ssn = ssn;
        Job.this.company = company;
        Job.this.salary = salary;
        Job.this.experience = experience;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
