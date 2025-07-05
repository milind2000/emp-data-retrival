package com.rcm.emp_managment.repository;

import com.rcm.emp_managment.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // only department filter
    @Query("SELECT DISTINCT e FROM Employee e " +
            "LEFT JOIN e.projects p " +
            "LEFT JOIN e.performanceReviews pr " +
            "WHERE (:departments IS NULL OR e.department.id IN :departments) " +
            "AND (:projects IS NULL OR p.id IN :projects) " +
            "AND (:reviewDate IS NULL OR pr.reviewDate = :reviewDate) " +
            "AND (:minScore IS NULL OR pr.score IS NULL OR pr.score = :minScore)")
    List<Employee> findEmployeesByFilter(@Param("departments") List<Long> departments,
                                         @Param("projects") List<Long> projects,
                                         @Param("reviewDate") LocalDate reviewDate,
                                         @Param("minScore") Integer minScore);

    @Query("SELECT e FROM Employee e " +
            "LEFT JOIN FETCH e.department " +
            "LEFT JOIN FETCH e.projects " +
            "WHERE e.id = :id")
    Optional<Employee> fetchEmployeeWithDepartmentAndProjects(@Param("id") Long id);


}
