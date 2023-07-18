package com.dmochowski.crewmanagement.Service;

import com.dmochowski.crewmanagement.entity.ArchivalTask;

import java.util.List;

public interface TaskService {
    ArchivalTask save(ArchivalTask archivalTask);
    List<ArchivalTask> findAll();
    List<ArchivalTask> findAllByEmployeeId(int id);
}
