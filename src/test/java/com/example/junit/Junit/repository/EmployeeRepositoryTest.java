package com.example.junit.Junit.repository;

import com.example.junit.Junit.model.Employee;
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


    /*
    void given_when_then(){

     ..this is the naming convention that we will be following

     }
      */
    @Test // junit test for save employee operation
    @DisplayName("Save Employee Operation")
    void givenEmployeeObject_whenSave_thenReturnSavedEmployeeObject() {
        //1. given- precondition or setup

        Employee employee = Employee.builder()
                .id(1L)
                .firstName("Ramesh")
                .lastName("Ramesh")
                .email("ramesh@gmail.com")
                .build();
        //2. when - Behaviour or action that we are going to test
        Employee savedEmployee = employeeRepository.save(employee);

        //3. then - verify object
        //import org.assertj.core.api.Assertions;
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isPositive();


    }

    @Test
    @DisplayName("Find All Operation")
    void givenEmployeeList_whenFindAll_thenEmployeeList() {
        //given
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("Ramesh")
                .lastName("Ramesh")
                .email("ramesh@gmail.com")
                .build();
        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("prata")
                .lastName("Ramaaaasdsdsesh")
                .email("rameasawssdsh@gmail.com")
                .build();


        employeeRepository.save(employee);
        employeeRepository.save(employee1);
        //when
        List<Employee> listOfEMployeee = employeeRepository.findAll();

        //then
        assertThat(listOfEMployeee).isNotNull();
        assertThat(listOfEMployeee.size()).isEqualTo(2);

    }


    @Test
    @DisplayName("FindById Operation")
    void givenEmployeeObject_whenFindByID_thenReturnEmployeeObject() {
        //1. given- precondition or setup
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("Ramesh")
                .lastName("Ramesh")
                .email("ramesh@gmail.com")
                .build();
        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("prata")
                .lastName("Ramaaaasdsdsesh")
                .email("rameasawssdsh@gmail.com")
                .build();


        employeeRepository.save(employee);
        employeeRepository.save(employee1);
        //2. when - Behaviour or action that we are going to test
        Optional<Employee> byId = employeeRepository.findById(1L);

        //3. then - verify object

        //import org.assertj.core.api.Assertions;
        assertThat(byId.get()).isNotNull();
    }

    @Test
    @DisplayName("Find By Email Operation")
    void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() {
        //1. given- precondition or setup
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("Ramesh")
                .lastName("Ramesh")
                .email("ramesh@gmail.com")
                .build();

        employeeRepository.save(employee);

        //2. when - Behaviour or action that we are going to test
        Optional<Employee> byEmail = employeeRepository.findByEmail("ramesh@gmail.com");
        //3. then - verify object
        assertThat(byEmail.get()).isNotNull();
        //import org.assertj.core.api.Assertions;

    }

    @Test
    @DisplayName("Update Operation")
    void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {
        //1. given- precondition or setup
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("Ramesh")
                .lastName("Ramesh")
                .email("ramesh@gmail.com")
                .build();

        employeeRepository.save(employee);
        //2. when - Behaviour or action that we are going to test
        Employee updatedEmployee = employeeRepository.findById(employee.getId()).get();
        updatedEmployee.setEmail("Pratap");
        updatedEmployee.setFirstName("pratap");

        Employee saved = employeeRepository.save(updatedEmployee);

        //3. then - verify object
        assertThat(saved).isNotNull();
        assertThat(saved.getEmail()).isEqualTo("Pratap");
        //import org.assertj.core.api.Assertions;

    }

    @Test
    @DisplayName("Delete")
    void given_when_then() {
        //1. given- precondition or setup
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("Ramesh")
                .lastName("Ramesh")
                .email("ramesh@gmail.com")
                .build();

        employeeRepository.save(employee);
        //2. when - Behaviour or action that we are going to test
        employeeRepository.delete(employee);
        //3. then - verify object
        assertThat(employeeRepository.findAll().size()).isLessThan(1);
        //import org.assertj.core.api.Assertions;

    }
}