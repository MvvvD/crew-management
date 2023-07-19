package com.dmochowski.crewmanagement.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "employees")
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

    @Column(name = "hired_since")
    private java.sql.Date hiredSince;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "current_task")
    private String task;

    @Column(name = "task_timestamp")
    private java.sql.Timestamp taskTimestamp;

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

    public Date getHiredSince() {
        return hiredSince;
    }

    public void setHiredSince(Date hiredSince) {
        this.hiredSince = hiredSince;
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

    public Timestamp getTaskTimestamp() {
        return taskTimestamp;
    }

    public void setTaskTimestamp(Timestamp taskTimestamp) {
        this.taskTimestamp = taskTimestamp;
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
                ", since = '" + taskTimestamp + '\'' +
                '}';
    }
}

