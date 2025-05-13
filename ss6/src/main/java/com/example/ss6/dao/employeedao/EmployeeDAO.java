package com.example.ss6.dao.employeedao;

import com.example.ss6.model.Employee;
import java.util.List;
import java.util.Map;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(int id);
    boolean save(Employee employee);
    boolean update(Employee employee);
    boolean deleteById(int id);
    List<Employee> findByName(String name);
    int countAll();
    List<Employee> findPaged(int page, int pageSize, String sortField, String sortDirection);
    Map<String, Object> getPagedData(int page, int pageSize, String sortField, String sortDirection);
}
