package com.example.employeecrud.service;

import com.example.employeecrud.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees(String keyword);

    Employee getEmployeeById(Long id);

    Employee saveEmployee(Employee employee);

    void deleteEmployee(Long id);

    boolean isEmailTaken(String email, Long currentId);
}
