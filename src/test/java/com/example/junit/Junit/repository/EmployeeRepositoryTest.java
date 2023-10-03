package com.example.junit.Junit.repository;

import com.example.junit.Junit.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest // to test repository layer
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;


    @BeforeEach
    void setEmployeeRepository() {
        employee = Employee.builder()
                .id(1L)
                .firstName("Ramesh")
                .lastName("Ramesh")
                .email("ramesh@gmail.com")
                .build();
    }

    @Test // junit test for save employee operation
    @DisplayName("Save Employee Operation")
    void givenEmployeeObject_whenSave_thenReturnSavedEmployeeObject() {

        Employee savedEmployee = employeeRepository.save(employee);

        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isPositive();


    }

    @Test
    @DisplayName("Find All Operation")
    void givenEmployeeList_whenFindAll_thenEmployeeList() {

        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("prata")
                .lastName("Ramaaaasdsdsesh")
                .email("rameasawssdsh@gmail.com")
                .build();


        employeeRepository.save(employee);
        employeeRepository.save(employee1);

        List<Employee> listOfEMployeee = employeeRepository.findAll();


        assertThat(listOfEMployeee).isNotNull();
        assertThat(listOfEMployeee.size()).isEqualTo(2);

    }


    @Test
    @DisplayName("FindById Operation")
    void givenEmployeeObject_whenFindByID_thenReturnEmployeeObject() {
        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("prata")
                .lastName("Ramaaaasdsdsesh")
                .email("rameasawssdsh@gmail.com")
                .build();


        employeeRepository.save(employee);
        employeeRepository.save(employee1);

        Optional<Employee> byId = employeeRepository.findById(1L);


        assertThat(byId.get()).isNotNull();
    }

    @Test
    @DisplayName("Find By Email Operation")
    void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() {


        employeeRepository.save(employee);


        Optional<Employee> byEmail = employeeRepository.findByEmail("ramesh@gmail.com");

        assertThat(byEmail.get()).isNotNull();


    }

    @Test
    @DisplayName("Update Operation")
    void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {


        employeeRepository.save(employee);

        Employee updatedEmployee = employeeRepository.findById(employee.getId()).get();
        updatedEmployee.setEmail("Pratap");
        updatedEmployee.setFirstName("pratap");

        Employee saved = employeeRepository.save(updatedEmployee);


        assertThat(saved).isNotNull();
        assertThat(saved.getEmail()).isEqualTo("Pratap");


    }

    @Test
    @DisplayName("Delete")
    void given_when_then() {


        employeeRepository.save(employee);

        employeeRepository.delete(employee);

        assertThat(employeeRepository.findAll().size()).isLessThan(1);


    }

    @Test
    @DisplayName("Unit test case Name")
    void givenFirstNameAndLastName_whenUsingJPQL_thenReturnEmployee() {

        employeeRepository.save(employee);
        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("pratap")
                .lastName("pratap")
                .email("ramesh@gmail1.com")
                .build();

        employeeRepository.save(employee1);

        Employee employeeRepositoryByJPQL = employeeRepository.findByJPQL("pratap", "pratap");


        assertThat(employeeRepositoryByJPQL).isNotNull();
    }

    @Test
    @DisplayName("Unit test case Name")
    void givenFirstNameAndLastName_whenUsingJPQLNamedParam_thenReturnEmployee() {
        //1. given- precondition or setup


        employeeRepository.save(employee);
        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("pratap")
                .lastName("pratap")
                .email("ramesh@gmail1.com")
                .build();

        employeeRepository.save(employee1);

        Employee employeeRepositoryByJPQL = employeeRepository.findByJPQLNamedParams("pratap", "pratap");

        assertThat(employeeRepositoryByJPQL).isNotNull();
    }
}