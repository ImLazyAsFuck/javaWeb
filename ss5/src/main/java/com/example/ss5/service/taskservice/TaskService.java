package com.example.ss5.service.taskservice;

import com.example.ss5.model.Task;

import java.util.List;

public interface TaskService{
    List<Task> findAll();
    boolean save(Task task);
    boolean delete(int id);
    boolean update(Task task);
}
