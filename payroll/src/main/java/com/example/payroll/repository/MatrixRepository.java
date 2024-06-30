package com.example.payroll.repository;

import com.example.payroll.model.MatrixSalary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatrixRepository extends JpaRepository <MatrixSalary, Long> {
    Optional<MatrixSalary> findByGrade(int grade);

}




