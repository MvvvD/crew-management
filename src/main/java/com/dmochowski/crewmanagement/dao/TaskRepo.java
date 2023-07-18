package com.dmochowski.crewmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dmochowski.crewmanagement.entity.ArchivalTask;

import java.util.List;

public interface TaskRepo extends JpaRepository<ArchivalTask, Integer> {
    List<ArchivalTask> findByEmployeeId(int id);

}
