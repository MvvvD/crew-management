package com.dmochowski.crewmanagement.entity;

public class EmployeeGdpr {
    //basically an employee class without sensitive/GDPR protected data,
    // might be a feature for a client "oversight" or something, only gets Get HTTP methods and getters,
    // employeesGDPR are created from employees on server side, never exposing sensitive data to the client.

    private final String name, role, task;

    public EmployeeGdpr(Employee employee) {
        this.name = employee.getFirstName();
        this.role = employee.getRole();
        this.task = employee.getTask();
    }

    public String getName() {
        return name;
    }


    public String getRole() {
        return role;
    }


    public String getTask() {
        return task;
    }


    @Override
    public String toString() {
        return "Employee{" +
                ", firstName = '" + name + '\'' +
                ", role = '" + role + '\'' +
                ", current task = '" + task + '\'' +
                '}';
    }
}
