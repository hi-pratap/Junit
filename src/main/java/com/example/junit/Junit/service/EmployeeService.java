package com.example.junit.Junit.service;

import com.example.junit.Junit.model.Employee;
import com.example.junit.Junit.repository.EmployeeRepository;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployee();
    Employee getEmployeeByID(Long id);
    Employee updateEmployee(Employee employee);
}
