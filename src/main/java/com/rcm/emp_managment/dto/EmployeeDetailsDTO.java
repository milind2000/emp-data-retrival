package com.rcm.emp_managment.dto;

import java.time.LocalDate;
import java.util.List;

public class EmployeeDetailsDTO {
    private Long employeeId;
    private String employeeName;
    private String departmentName;
    private List<String> projectNames;
    private List<com.rcm.emp_managment.dto.ReviewDTO> lastThreeReviews;

    public EmployeeDetailsDTO(Long employeeId, String employeeName, String departmentName, List<String> projectNames, List<ReviewDTO> lastThreeReviews) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.departmentName = departmentName;
        this.projectNames = projectNames;
        this.lastThreeReviews = lastThreeReviews;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<String> getProjectNames() {
        return projectNames;
    }

    public void setProjectNames(List<String> projectNames) {
        this.projectNames = projectNames;
    }

    public List<ReviewDTO> getLastThreeReviews() {
        return lastThreeReviews;
    }

    public void setLastThreeReviews(List<ReviewDTO> lastThreeReviews) {
        this.lastThreeReviews = lastThreeReviews;
    }
}
