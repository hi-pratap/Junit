package com.example.junit.Junit.repository;

import com.example.junit.Junit.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    //WIith index
    @Query("select e from Employee e where e.firstName=?1 and e.lastName=?2")
    Employee findByJPQL(String firstName, String lastName);

    @Query("select e from Employee e where e.firstName=:firstName and e.lastName=:lastName")
    Employee findByJPQLNamedParams(String firstName, String lastName);



}