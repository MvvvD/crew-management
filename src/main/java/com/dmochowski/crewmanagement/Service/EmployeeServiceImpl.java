package com.dmochowski.crewmanagement.Service;

import com.dmochowski.crewmanagement.dao.EmployeeRepo;
import com.dmochowski.crewmanagement.entity.Employee;
import com.dmochowski.crewmanagement.entity.EmployeeGdpr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    private List<EmployeeGdpr> EmployeesToEmployeesGdpr(List<Employee> employees) {
        List<EmployeeGdpr> employeeGdpr = new ArrayList<>();
        employees.forEach(employee -> employeeGdpr.add(new EmployeeGdpr(employee)));
        return employeeGdpr;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @Override
    public List<EmployeeGdpr> findAllGdpr() {
        List<Employee> employees = employeeRepo.findAll();
        return EmployeesToEmployeesGdpr(employees);
    }

    @Override
    public Employee findById(int id) {

        Optional<Employee> request = employeeRepo.findById(id);
        Employee employee;
        if (request.isPresent()) {
            employee = request.get();
        } else {
            throw new RuntimeException("Employee not found");
        }
        return employee;
    }

    @Override
    public EmployeeGdpr findByIdGdpr(int id) {
        Optional<Employee> request = employeeRepo.findById(id);
        EmployeeGdpr employee;
        if (request.isPresent()) {
            employee = new EmployeeGdpr(request.get());
        } else {
            throw new RuntimeException("Employee not found");
        }
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public void delete(Employee employee) {
        employeeRepo.delete(employee);
    }

    @Override
    public List<Employee> findByRole(String role) {
        return employeeRepo.findByRole(role);
    }

    @Override
    public List<EmployeeGdpr> findByRoleGdpr(String role) {
        List<Employee> employees = employeeRepo.findByRole(role);
        return EmployeesToEmployeesGdpr(employees);
    }

    @Override
    public List<Employee> findByTaskNull() {
        return employeeRepo.findByTaskIsNull();
    }

    @Override
    public List<Employee> findByTaskNotNull() {
        return employeeRepo.findByTaskIsNotNull();
    }

    @Override
    public List<EmployeeGdpr> findAvailableEmployeesGdpr() {
        return EmployeesToEmployeesGdpr(employeeRepo.findByTaskIsNull());
    }
}
