package com.example.employeecrud.repository;

import com.example.employeecrud.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmailIgnoreCase(String email);

    @Query("""
           SELECT e FROM Employee e
           WHERE LOWER(e.firstName)  LIKE LOWER(CONCAT('%', :keyword, '%'))
              OR LOWER(e.lastName)   LIKE LOWER(CONCAT('%', :keyword, '%'))
              OR LOWER(e.email)      LIKE LOWER(CONCAT('%', :keyword, '%'))
              OR LOWER(e.department) LIKE LOWER(CONCAT('%', :keyword, '%'))
           ORDER BY e.id
           """)
    List<Employee> search(@Param("keyword") String keyword);
}
