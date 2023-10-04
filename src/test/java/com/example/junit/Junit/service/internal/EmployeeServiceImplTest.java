package com.example.junit.Junit.service.internal;

import com.example.junit.Junit.exception.ResourceNotFoundException;
import com.example.junit.Junit.model.Employee;
import com.example.junit.Junit.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;
    private Employee employee;

    @BeforeEach
    public void setup() {
        employee = Employee.builder()
                .id(1l)
                .firstName("Pratap")
                .lastName("Adhav")
                .email("email@email")
                .build();
    }

    @Test
    @DisplayName("Unit test case Name")
    void givenEmployeeObject_whenSaveEmploye_thenReturnEmployee() {

        //stubbing methods
        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.empty());
        given(employeeRepository.save(employee)).willReturn(employee);

        //when
        Employee savedEmployee = employeeService.saveEmployee(employee);

        //then
        Assertions.assertThat(savedEmployee).isNotNull();

    }

    @Test
    @DisplayName("Exception throws")
    void givenExistingEmail_whenSaveEmploye_thenThrowsException() {

        //stubbing methods
        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.of(employee));

        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.saveEmployee(employee);
        });
        // this last method should not get executed in the class
        verify(employeeRepository, never()).save(any(Employee.class));

    }

    @Test
    @DisplayName("Unit test case Name")
    void givenEmployeeList_whenGetAllEmployee_thenReturnEmployeeLists() {
        Employee employee2 = Employee.builder()
                .id(2l)
                .firstName("Pratap1")
                .lastName("Adhav1")
                .email("email@email1")
                .build();
        Employee employee3 = Employee.builder()
                .id(3l)
                .firstName("Pratap2")
                .lastName("Adhav2")
                .email("email@email2")
                .build();
        Employee employee4 = Employee.builder()
                .id(4l)
                .firstName("Pratap3")
                .lastName("Adhav3")
                .email("email@email3")
                .build();
        given(employeeRepository.findAll()).willReturn(Arrays.asList(employee4, employee3, employee2, employee));

//when
        List<Employee> allEmployee = employeeService.getAllEmployee();

        Assertions.assertThat(allEmployee.size()).isEqualTo(4);
    }
}