# crew-management WORK IN PROGRESS 
First non-tutorial project in Spring. Whole project was done by myself, without any guidance/overview 

Back-end for a crew management app  
that allows for (depending on role/permissions):  
CRUD operation on employee,
reading GDPR-safe employee data without any authentication
reading, updating and "marking as done" on employee tasks  
getting employees list, possible filtering by role/working status   
tasks tracking and automatic archiving of finished tasks

Stack:
* Java 18
* Spring Boot 3
* MySQL
* Maven

Tools:
* IntelliJ idea
* Postman
* MySQL workbench

Entities:
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

Roles:
* ADMIN 
* MANAGER
* SUPERVISOR
* WORKER

Endpoints:
* /employees  
  * GET → return list of employees, roles: all
  * POST → updates employees, roles: ADMIN, MANAGER, requires employee (without id) in body 
  * PUT →  adds employees, roles: ADMIN, requires employee in body
  * DELETE → return list of employees, roles: ADMIN, requires employee in body    
 
    
* /employees/{id}
    * GET → return employee with given id if exist, roles: all


* /employees/roles/{role}
  * GET → return list of employees with given role if exist, roles: all


* /employees/available
    * GET → return list of employees without task, roles: all


* /employees/busy
    * GET → return list of employees with task, roles: all


* /employeesgdpr
  * GET → return list of employeesgdpr without sensitive data, no authentication required
  

* /employeesgdpr/{id}
    * GET → return employeegdpr without sensitive data with given id, no authentication required

  
* /employeesgdpr/available
  * GET → return list of employeesgdpr without task, no authentication required


* /tasks/{id}
    * GET → return currentTask of the employee with given id, roles: all
    * PUT → updates currentTask of the employee with given id using taskContent from current_task from body (moves employee from available to busy if available), roles: ADMIN, MANAGER, SUPERVISOR", requires current_task in body


* /tasks/{id}/finished
    * PUT → updates currentTask of the employee with given id to null, functionally marks task as done (moves employee from busy to available, adds finished task to task archive), roles: ADMIN, MANAGER, SUPERVISOR", requires current_task in body


* /tasks/repository
    * GET → return list of tasks, roles: all


* /tasks/repository/{id}
    * GET → return list of tasks finished by the employee with given id, roles: all
