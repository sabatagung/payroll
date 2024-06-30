package com.example.payroll.repository;

import com.example.payroll.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface PayrollRepository extends JpaRepository <Payroll, Long> {
    List<Payroll> findByEmployeeId(Long employeeId);
    List<Payroll> findByEmployeeIdAndDateBetween(Long employeeId, Date startDate, Date endDate);
}
