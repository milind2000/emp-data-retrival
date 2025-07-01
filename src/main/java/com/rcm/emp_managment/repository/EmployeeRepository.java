package com.rcm.emp_managment.repository;

import com.rcm.emp_managment.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
