package com.dmochowski.crewmanagement.Service;

import com.dmochowski.crewmanagement.entity.Employee;
import com.dmochowski.crewmanagement.entity.EmployeeGdpr;

import java.util.List;
public interface EmployeeService {

    List<Employee> findAll();
    List<EmployeeGdpr> findAllGdpr();
    Employee findById(int id);
    EmployeeGdpr findByIdGdpr(int id);
    Employee save(Employee employee);
    List<Employee> findByRole(String role);
    List<EmployeeGdpr> findByRoleGdpr(String role);
    List<Employee> findByTaskNull();
    List<Employee> findByTaskNotNull();
    List<EmployeeGdpr> findAvailableEmployeesGdpr();
    void delete(Employee employee);

}
