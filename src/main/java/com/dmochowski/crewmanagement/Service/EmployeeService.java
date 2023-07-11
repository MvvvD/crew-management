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
    List<Employee> findAvailableEmployees();
    List<EmployeeGdpr> findAvailableEmployeesGdpr();
    void deleteById(int id);

}
// TODO: 12/07/2023 security config with admin, hr, leader, worker, client roles