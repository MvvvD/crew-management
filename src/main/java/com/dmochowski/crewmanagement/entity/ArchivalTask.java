package com.dmochowski.crewmanagement.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name="tasks")
public class ArchivalTask {
    public ArchivalTask() {
    }

    public ArchivalTask(String taskDesc, int employeeId, Timestamp startTimestamp, Timestamp finishTimestamp) {
        this.taskDesc = taskDesc;
        this.employeeId = employeeId;
        this.startTimestamp = startTimestamp;
        this.finishTimestamp = finishTimestamp;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String taskDesc;

    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "start_timestamp")
    private java.sql.Timestamp startTimestamp;

    @Column(name = "finish_timestamp")
    private java.sql.Timestamp finishTimestamp;

    public int getId() {
        return id;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public Timestamp getStartTimestamp() {
        return startTimestamp;
    }

    public Timestamp getFinishTimestamp() {
        return finishTimestamp;
    }
}
