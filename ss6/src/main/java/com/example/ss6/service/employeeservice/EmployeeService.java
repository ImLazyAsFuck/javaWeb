package com.example.ss6.service.employeeservice;

import com.example.ss6.model.Employee;
import java.util.List;
import java.util.Map;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int id);
    boolean save(Employee employee);
    boolean update(Employee employee);
    boolean deleteById(int id);
    List<Employee> findByName(String name);
    Map<String, Object> getPagedEmployees(int page, int pageSize, String sortField, String sortDirection);
}
