package com.rcm.emp_managment.service;

import com.rcm.emp_managment.dto.EmployeeDetailsDTO;
import com.rcm.emp_managment.dto.ReviewDTO;
import com.rcm.emp_managment.entity.Employee;
import com.rcm.emp_managment.entity.Project;
import com.rcm.emp_managment.repository.EmployeeRepository;
import com.rcm.emp_managment.repository.PerformanceReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PerformanceReviewRepository performanceReviewRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, PerformanceReviewRepository performanceReviewRepository) {
        this.employeeRepository = employeeRepository;
        this.performanceReviewRepository = performanceReviewRepository;
    }

    public List<Employee> getAllEmployees(
            List<Long> departments,
            List<Long> projects,
            LocalDate reviewDate,
            Integer minScore
    ) {
        boolean hasDepts = departments != null && !departments.isEmpty();
        boolean hasProjects = projects != null && !projects.isEmpty();
        boolean hasReview = reviewDate != null && minScore != null;


        if (!hasDepts && !hasProjects && !hasReview) {
            // no filters → all employees
            return employeeRepository.findAll();
        }

        return employeeRepository.findEmployeesByFilter(departments, projects, reviewDate, minScore);
    }

    public EmployeeDetailsDTO getEmployeeDetails(Long employeeId) {
        Employee employee = employeeRepository.fetchEmployeeWithDepartmentAndProjects(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        List<ReviewDTO> reviews = performanceReviewRepository.findTop3ByEmployeeId(employeeId, PageRequest.of(0, 3));

        return new EmployeeDetailsDTO(
                employee.getId(),
                employee.getName(),
                employee.getDepartment().getName(),
                employee.getProjects().stream().map(Project::getName).toList(),
                reviews
        );
    }

}

