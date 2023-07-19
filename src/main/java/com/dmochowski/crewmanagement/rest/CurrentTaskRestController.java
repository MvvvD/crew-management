package com.dmochowski.crewmanagement.rest;

import com.dmochowski.crewmanagement.Service.EmployeeService;
import com.dmochowski.crewmanagement.Service.TaskService;
import com.dmochowski.crewmanagement.entity.ArchivalTask;
import com.dmochowski.crewmanagement.entity.Employee;
import com.dmochowski.crewmanagement.entity.CurrentTask;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class CurrentTaskRestController {
    private final EmployeeService employeeService;
    private final TaskService taskService;

    public CurrentTaskRestController(EmployeeService employeeService, TaskService taskService) {
        this.employeeService = employeeService;
        this.taskService = taskService;

    }

    private Employee getEmployee(int id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            throw new RuntimeException("no employee found");
        }
        return employee;
    }

    @GetMapping("/repository")
    public List<ArchivalTask> getFinished() {
        return taskService.findAll();
    }

    @GetMapping("/repository/{id}")
    public List<ArchivalTask> getFinishedByEmployeeId(@PathVariable int id) {
        return taskService.findAllByEmployeeId(id);
    }

    @GetMapping("/{id}")
    public CurrentTask getTask(@PathVariable int id) {
        return new CurrentTask(employeeService.findById(id).getTask());
    }

    // allows for simple {"currentTask":"task content"} json instead of whole employee request
    @PutMapping("/{id}")
    public Employee updateTask(@PathVariable int id, @RequestBody CurrentTask currentTask) {
        Employee employee = getEmployee(id);
        employee.setTask(currentTask.getCurrentTask());
        employee.setTaskTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        return employeeService.save(employee);
    }

    //this might be connected to a button or something, so supervisor can finish its task w/o body in request
    //sets task to null, so /available request gonna work properly
    @PutMapping("/{id}/finished")
    public ArchivalTask finishTask(@PathVariable int id) {
        Employee employee = getEmployee(id);
        if (employee.getTask() == null) {
            throw new RuntimeException("no task assigned");
        } else {
            ArchivalTask archivalTask = new ArchivalTask(employee.getTask(), employee.getId(), employee.getTaskTimestamp(), Timestamp.valueOf(LocalDateTime.now()));
            taskService.save(archivalTask);
            employee.setTask(null);
            employee.setTaskTimestamp(null);
            return taskService.save(archivalTask);
        }
    }
}
