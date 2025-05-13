package com.example.ss6.dao.employeedao;

import com.example.ss6.model.Employee;
import com.example.ss6.utils.DBConnect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public List<Employee> findAll() {
        Connection con = null;
        CallableStatement cs = null;
        List<Employee> employees = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call find_all_employees()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                employees.add(extractEmployeeFromResultSet(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, cs);
        }
        return employees;
    }

    @Override
    public Employee findById(int id) {
        Connection con = null;
        CallableStatement cs = null;
        Employee employee = null;
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call find_employee_by_id(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                employee = extractEmployeeFromResultSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, cs);
        }
        return employee;
    }

    @Override
    public boolean save(Employee employee) {
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call add_employee(?, ?, ?, ?, ?, ?)}");
            cs.setString(1, employee.getName());
            cs.setObject(2, employee.getBirthday());
            cs.setString(3, employee.getPhone());
            cs.setString(4, employee.getEmail());
            cs.setDouble(5, employee.getSalary());
            cs.setString(6, employee.getPosition());
            return cs.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, cs);
        }
        return false;
    }

    @Override
    public boolean update(Employee employee) {
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call update_employee(?, ?, ?, ?, ?, ?, ?)}");
            cs.setInt(1, employee.getId());
            cs.setString(2, employee.getName());
            cs.setObject(3, employee.getBirthday());
            cs.setString(4, employee.getPhone());
            cs.setString(5, employee.getEmail());
            cs.setDouble(6, employee.getSalary());
            cs.setString(7, employee.getPosition());
            return cs.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, cs);
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call delete_employee(?)}");
            cs.setInt(1, id);
            return cs.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, cs);
        }
        return false;
    }

    @Override
    public List<Employee> findByName(String name) {
        Connection con = null;
        CallableStatement cs = null;
        List<Employee> employees = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call find_employees_by_name(?)}");
            cs.setString(1, name);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                employees.add(extractEmployeeFromResultSet(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, cs);
        }
        return employees;
    }

    @Override
    public int countAll() {
        Connection con = null;
        CallableStatement cs = null;
        int count = 0;
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call count_employees()}");
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, cs);
        }
        return count;
    }

    @Override
    public List<Employee> findPaged(int page, int pageSize, String sortField, String sortDirection) {
        Connection con = null;
        CallableStatement cs = null;
        List<Employee> employees = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call find_employees_paged(?, ?, ?, ?)}");
            int offset = (page - 1) * pageSize;
            cs.setInt(1, offset);
            cs.setInt(2, pageSize);
            cs.setString(3, sortField != null && !sortField.isEmpty() ? sortField : "id");
            cs.setString(4, "DESC".equalsIgnoreCase(sortDirection) ? "DESC" : "ASC");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                employees.add(extractEmployeeFromResultSet(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, cs);
        }
        return employees;
    }

    @Override
    public Map<String, Object> getPagedData(int page, int pageSize, String sortField, String sortDirection) {
        Map<String, Object> result = new HashMap<>();
        int totalEmployees = countAll();
        int totalPages = (int) Math.ceil((double) totalEmployees / pageSize);
        
        if (page < 1) page = 1;
        if (page > totalPages && totalPages > 0) page = totalPages;
        
        List<Employee> employees = findPaged(page, pageSize, sortField, sortDirection);
        
        result.put("employees", employees);
        result.put("currentPage", page);
        result.put("totalPages", totalPages);
        result.put("totalItems", totalEmployees);
        result.put("sortField", sortField);
        result.put("sortDirection", sortDirection);
        
        return result;
    }

    private Employee extractEmployeeFromResultSet(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setBirthday(rs.getObject("birthday", LocalDate.class));
        employee.setPhone(rs.getString("phone"));
        employee.setEmail(rs.getString("email"));
        employee.setSalary(rs.getDouble("salary"));
        employee.setPosition(rs.getString("position"));
        return employee;
    }
}
