package com.dmochowski.crewmanagement.Service;

import com.dmochowski.crewmanagement.dao.TaskRepo;
import com.dmochowski.crewmanagement.entity.ArchivalTask;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;

    @Autowired
    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @Override
    public List<ArchivalTask> findAll() {
        return taskRepo.findAll();
    }

    @Override
    public List<ArchivalTask> findAllByEmployeeId(int id) {
        return taskRepo.findByEmployeeId(id);

    }

    @Override
    @Transactional
    public ArchivalTask save(ArchivalTask archivalTask) {
        return taskRepo.save(archivalTask);
    }
}
