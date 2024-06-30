package com.example.payroll.service;


import com.example.payroll.dto.PayrollResponseDTO;
import com.example.payroll.model.Employee;
import com.example.payroll.model.Payroll;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PayrollService {
    PayrollResponseDTO addPayroll(Long employeeId, int dayOfPresent, int dayOfAbsence, Date date);
//    List <Payroll> getAllPayroll ();
    List<Payroll> getAllPayroll();
    List<PayrollResponseDTO> getPayrollsByEmployeeId(Long employeeId);
    Optional<Employee> getById (Long id);
}
