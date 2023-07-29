package com.dmochowski.crewmanagement.rest;


import com.dmochowski.crewmanagement.service.EmployeeService;
import com.dmochowski.crewmanagement.entity.EmployeeGdpr;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employeesgdpr")
public class EmployeeGdprRestController {

    private final EmployeeService employeeService;

    public EmployeeGdprRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping()
    public List<EmployeeGdpr> listEmployeesGdpr() {

        return employeeService.findAllGdpr();
    }

    @GetMapping("/{id}")
    public EmployeeGdpr getEmployeeGdpr(@PathVariable int id){
        EmployeeGdpr employee = employeeService.findByIdGdpr(id);
        if(employee==null){
            throw new RuntimeException("no employee " +id+" found");
        }
        return employee;
    }


    @GetMapping("role/{role}")
    public List<EmployeeGdpr> getRole(@PathVariable String role){
        return employeeService.findByRoleGdpr(role);
    }

    @GetMapping("/available")
    public List<EmployeeGdpr> getAvailable(){
        return employeeService.findAvailableEmployeesGdpr();
    }
}
