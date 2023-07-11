package com.dmochowski.crewmanagement.rest;


import com.dmochowski.crewmanagement.Service.EmployeeService;

import com.dmochowski.crewmanagement.entity.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeRestController {

    private EmployeeService employeeService;

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
        return employeeService.save(employee);
    }


    @PutMapping()
    public Employee editEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @GetMapping("role/{role}")
    public List<Employee> getRole(@PathVariable String role){
        return employeeService.findByRole(role);
    }

    @GetMapping("/available")
    public List<Employee> getAvailable(){
        return employeeService.findAvailableEmployees();
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id){
        Employee employee = employeeService.findById(id);
        if(employee==null){
          throw new RuntimeException("no employee found");
        }
        employeeService.deleteById(id);
        return "deleted employee id: " + id;
    }
}
