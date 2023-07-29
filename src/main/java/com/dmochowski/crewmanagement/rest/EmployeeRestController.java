package com.dmochowski.crewmanagement.rest;


import com.dmochowski.crewmanagement.service.EmployeeService;

import com.dmochowski.crewmanagement.entity.Employee;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public List<Employee> listEmployees() {

        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable int id){
        Employee employee = employeeService.findById(id);
        if(employee==null){
            throw new RuntimeException("no employee " +id+" found");
        }
        return employee;
    }

    @PostMapping()
    public Employee addEmployee(@RequestBody Employee employee){
        employee.setId(0); //requesting SQL to create new id, just in case there is an id in body
        employee.setHiredSince(Date.valueOf(LocalDate.now()));
        return employeeService.save(employee);
    }


    @PutMapping()
    public Employee editEmployee(@RequestBody Employee employee){
        Employee employeeDb = employeeService.findById(employee.getId());
        if(employee==null){
            throw new RuntimeException("no employee " +employee.getId()+" found");
        }
        employee.setHiredSince(employeeDb.getHiredSince()); //changing date of hiring is prohibited
        return employeeService.save(employee);
    }

    @GetMapping("role/{role}")
    public List<Employee> getRole(@PathVariable String role){
        return employeeService.findByRole(role);
    }

    @GetMapping("/available")
    public List<Employee> getAvailable(){
        return employeeService.findByTaskNull();
    }
    @GetMapping("/busy")
    public List<Employee> getBusy(){
        return employeeService.findByTaskNotNull();
    }

    @DeleteMapping()
    public Employee deleteEmployee(@RequestBody Employee employee){


        employeeService.delete(employee);
        return employee;
    }
}
