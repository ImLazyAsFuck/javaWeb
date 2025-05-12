package com.example.ss5.service.taskservice;

import com.example.ss5.dao.taskdao.TaskDAO;
import com.example.ss5.dao.taskdao.TaskDAOImp;
import com.example.ss5.model.Task;

import java.util.List;

public class TaskServiceImp implements TaskService{
    private final TaskDAO TASK_DAO = new TaskDAOImp();
    @Override
    public List<Task> findAll(){
        return TASK_DAO.findAll();
    }

    @Override
    public boolean save(Task task){
        return TASK_DAO.save(task);
    }

    @Override
    public boolean delete(int id){
        return TASK_DAO.delete(id);
    }

    @Override
    public boolean update(Task task){
        return TASK_DAO.update(task);
    }
}
