package com.dmochowski.crewmanagement.rest;

import com.dmochowski.crewmanagement.Service.EmployeeService;
import com.dmochowski.crewmanagement.entity.Employee;
import com.dmochowski.crewmanagement.entity.CurrentTask;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class CurrentTaskRestController {
    private EmployeeService employeeService;

    public CurrentTaskRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/{id}")
    public CurrentTask getTask(@PathVariable int id){
        return new CurrentTask(employeeService.findById(id).getTask());
    }

    // allows for simple {"task":"task content"} json instead of whole employee request
    @PutMapping("/{id}")
    public Employee updateTask(@PathVariable int id, @RequestBody CurrentTask currentTask){
         Employee employee = employeeService.findById(id);
         employee.setTask(currentTask.getCurrentTask());
         return employeeService.save(employee);
    }

    //this might be connected to a button or something, so supervisor can finish its task w/o body in request
    //sets task to null, so /available request gonna work properly
    @PutMapping("/{id}/finished")
    public Employee finishTask(@PathVariable int id){
        Employee employee = employeeService.findById(id);
        employee.setTask(null);
        return employeeService.save(employee);
    }
}
