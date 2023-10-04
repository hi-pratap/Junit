package com.example.junit.Junit.service.internal;

import com.example.junit.Junit.exception.ResourceNotFoundException;
import com.example.junit.Junit.model.Employee;
import com.example.junit.Junit.repository.EmployeeRepository;
import com.example.junit.Junit.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository=employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {

        Optional<Employee> byEmail = employeeRepository.findByEmail(employee.getEmail());
        if(byEmail.isPresent()){
            throw new ResourceNotFoundException("Already Exist");
        }

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {


        return employeeRepository.findAll();
    }
}
