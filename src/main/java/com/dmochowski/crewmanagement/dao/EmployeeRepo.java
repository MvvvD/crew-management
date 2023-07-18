package com.dmochowski.crewmanagement.dao;


import com.dmochowski.crewmanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
//    leaving zombie code for educational purposes

//    @Query(value = "select * from crew_management.employee where role = :role", nativeQuery = true)
//    List<Employee> findByRole(@Param("role") String r);

//    @Query(value = "select * from crew_management.employee where task is null", nativeQuery = true)
//    List<Employee> findAvailableEmployees();

    List<Employee> findByTaskIsNull();
    List<Employee> findByTaskIsNotNull();
    List<Employee> findByRole(String role);

}
