package com.springcrud.mvc.services;

import com.springcrud.mvc.models.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(Long Id);
    void deleteEmployee(Long Id);
}
