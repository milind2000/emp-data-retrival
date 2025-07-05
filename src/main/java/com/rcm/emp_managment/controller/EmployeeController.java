package com.rcm.emp_managment.controller;

import com.rcm.emp_managment.dto.EmployeeDetailsDTO;
import com.rcm.emp_managment.entity.Employee;
import com.rcm.emp_managment.service.EmployeeService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployeesHandler(
            @RequestParam(required = false) List<Long> departments,
            @RequestParam(required = false) List<Long> projects,
            @RequestParam(required = false) LocalDate reviewDate,
            @RequestParam(required = false) Integer minScore
    ) {
        return new ResponseEntity<>(employeeService.getAllEmployees(departments, projects, reviewDate, minScore), HttpStatus.FOUND);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDetailsDTO> getAllEmployeesHandler(
            @PathVariable Long employeeId
    ) {
        return new ResponseEntity<EmployeeDetailsDTO>(employeeService.getEmployeeDetails(employeeId), HttpStatus.FOUND);
    }

}
