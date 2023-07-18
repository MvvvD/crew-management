# crew-management 

_WORK IN PROGRESS_ 


First project in Spring boot. Project was done by myself, without any guidance nor was copy-pasted from a tutorial. Problems were solved with help of already existing stack overflow questions or official java/spring boot/mysql documentation

**Back-end/API** for a crew management app that allows for (depending on role/permissions):  
* CRUD operation on employee
* reading GDPR-safe employee data without any authentication
* reading, updating and "marking as done" on employee tasks  
* getting employees list, possible filtering by role/working status   
* tasks tracking and automatic archiving of finished tasks

## Stack/Tools:
* Java 18
* Spring Boot 3
* MySQL
* Maven

  
* IntelliJ idea
* Postman
* MySQL workbench

## Entities:

Data is formatted in JSON. SQL types in bracket for corresponding field in database.

* employee  
    {  
  "id" : "id", (INT)  
  "firstName" : "firstName", (VARCHAR(40))  
  "lastName" : "lastName", (VARCHAR(40))  
  "role" : "role", (VARCHAR(40))  
  "hiredSince" : "hiredSince", (DATE)  
  "phoneNumber" : "phoneNumber", (VARCHAR(40))  
  "task" : "currentTask", (VARCHAR(800))  
  "taskTimestamp" : "taskTimestamp", (DATETIME)  
}


* employeegdpr  
  {  
  "id" : "id", (INT)  
  "firstName" : "firstName", (VARCHAR(40))  
  "role" : "role", (VARCHAR(40))  
  "task" : "currentTask", (VARCHAR(800))  
  }


* task  
  {  
  "id" : "id", (INT)  
  "taskDesc" : "description", (VARCHAR(800))  
  "employeeId" : "employeeId", (INT)  
  "taskTimestamp" : "startTimestamp", (DATETIME)  
  "finishTimestamp" : "finishTimestamp", (DATETIME)  
  }


* current_task  
  {  
"currentTask" : "taskContent", (VARCHAR(800))  
}

Employeegdpr and task are **read-only** entities. Employeegdpr is basically employee entity stripped from sensitive data. Current_task is workaround for updating an employee with new task using request body that is as small as possible, simplifying said body to single field.

Employee **doesn't** have separate "task load status" field. Available Employee has currentTask set to **null**. This can be achieved using /tasks/{id}/finished endpoint with HTTP PUT method. This endpoint doesn't require body, so it can be wired, for example, to a button. Setting task to an empty string **won't** change status to available. **Employee with empty string as a task will be treated as busy.**


## Roles:
* ADMIN 
* MANAGER
* SUPERVISOR
* WORKER

**ADMIN** has all permissions, **MANAGER** can update employees and has Supervisor/Worker permissions. **SUPERVISOR** can update and "mark as done" tasks plus has Worker permissions. **WORKER** can get list of employees (and filter it by role/task load), get employee by id, check current and past tasks.

## REST endpoints:
* /employees  
  * GET → return list of employees, roles: all
  * POST → updates employees, roles: ADMIN, MANAGER, requires employee (without id) in body 
  * PUT →  adds employees, roles: ADMIN, requires employee in body
  * DELETE → return list of employees, roles: ADMIN, requires employee in body    
 
    
* /employees/{id}
    * GET → return employee with given id if exists, roles: all


* /employees/roles/{role}
  * GET → return list of employees with given role if exists, roles: all


* /employees/available
    * GET → return list of employees without task, roles: all


* /employees/busy
    * GET → return list of employees with task, roles: all


* /employeesgdpr
  * GET → return list of employeesgdpr without sensitive data, no authentication required
  

* /employeesgdpr/{id}
    * GET → return employeegdpr without sensitive data with given id, no authentication required

  
* /employeesgdpr/available
  * GET → return a list of employeesgdpr without task, no authentication required


* /tasks/{id}
    * GET → return currentTask of the employee with given id, roles: all
    * PUT → updates currentTask of the employee with given id using taskContent from current_task from body (moves employee from available to busy if available), roles: ADMIN, MANAGER, SUPERVISOR", requires current_task in body


* /tasks/{id}/finished
    * PUT → updates currentTask of the employee with given id to null, functionally marks task as done (moves employee from busy to available, adds finished task to task archive), roles: ADMIN, MANAGER, SUPERVISOR


* /tasks/repository
    * GET → return list of tasks, roles: all


* /tasks/repository/{id}
    * GET → return list of tasks finished by the employee with given id, roles: all
