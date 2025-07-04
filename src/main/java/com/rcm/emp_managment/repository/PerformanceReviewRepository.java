package com.rcm.emp_managment.repository;

import com.rcm.emp_managment.dto.EmployeeDetailsDTO;
import com.rcm.emp_managment.dto.ReviewDTO;
import com.rcm.emp_managment.entity.PerformanceReview;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PerformanceReviewRepository extends JpaRepository<PerformanceReview, Long> {

    @Query("SELECT new com.rcm.emp_managment.dto.ReviewDTO(pr.reviewDate, pr.score) " +
            "FROM PerformanceReview pr " +
            "WHERE pr.employee.id = :employeeId " +
            "ORDER BY pr.reviewDate DESC")
    List<ReviewDTO> findTop3ByEmployeeId(@Param("employeeId") Long employeeId, Pageable pageable);


}
