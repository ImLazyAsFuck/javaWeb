package com.example.ss5.dao.taskdao;

import com.example.ss5.model.Task;

import java.util.List;

public interface TaskDAO{
    List<Task> findAll();
    boolean save(Task task);
    boolean delete(int id);
    boolean update(Task task);
}
