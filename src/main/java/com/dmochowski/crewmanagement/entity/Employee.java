package com.dmochowski.crewmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    //for readability
    //int id, String firstName, lastName, role, phoneNumber, hiredSince, phoneNumber, task

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "role")
    private String role;

    @Column(name = "hired_from")
    private String hiredSince;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "task")
    private String task;

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fName) {
        this.firstName = fName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lName) {
        this.lastName = lName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getHiredSince() {
        return hiredSince;
    }

    public void setHiredSince(String hiredFrom) {
        this.hiredSince = hiredFrom;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id = " + id +
                ", firstName = '" + firstName + '\'' +
                ", lastName = '" + lastName + '\'' +
                ", role = '" + role + '\'' +
                ", working since = '" + hiredSince + '\'' +
                ", contact = '" + phoneNumber + '\'' +
                ", current task = '" + task + '\'' +
                '}';
    }
}

