package com.example.ss6.service.employeeservice;

import com.example.ss6.dao.employeedao.EmployeeDAO;
import com.example.ss6.dao.employeedao.EmployeeDAOImpl;
import com.example.ss6.model.Employee;

import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDAO EMPLOYEE_DAO = new EmployeeDAOImpl();
    
    @Override
    public List<Employee> findAll() {
        return EMPLOYEE_DAO.findAll();
    }
    
    @Override
    public Employee findById(int id) {
        return EMPLOYEE_DAO.findById(id);
    }
    
    @Override
    public boolean save(Employee employee) {
        return EMPLOYEE_DAO.save(employee);
    }
    
    @Override
    public boolean update(Employee employee) {
        return EMPLOYEE_DAO.update(employee);
    }
    
    @Override
    public boolean deleteById(int id) {
        return EMPLOYEE_DAO.deleteById(id);
    }
    
    @Override
    public List<Employee> findByName(String name) {
        return EMPLOYEE_DAO.findByName(name);
    }
    
    @Override
    public Map<String, Object> getPagedEmployees(int page, int pageSize, String sortField, String sortDirection) {
        return EMPLOYEE_DAO.getPagedData(page, pageSize, sortField, sortDirection);
    }
}
