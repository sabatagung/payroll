package com.example.payroll.controller;

import com.example.payroll.dto.PayrollRequestDTO;
import com.example.payroll.dto.PayrollResponseDTO;
import com.example.payroll.model.Employee;
import com.example.payroll.model.MatrixSalary;
import com.example.payroll.model.Payroll;
import com.example.payroll.service.EmployeeService;
import com.example.payroll.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payroll")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public PayrollResponseDTO addPayroll(@RequestBody PayrollRequestDTO payrollRequest) {
        return payrollService.addPayroll(payrollRequest.getEmployeeId(), payrollRequest.getDayOfPresent(), payrollRequest.getDayOfAbsence(), payrollRequest.getDate());
    }

    @GetMapping()
    public List<Payroll> getAllPayroll() {
        return payrollService.getAllPayroll();
    }

    @GetMapping ("{employee-id}")
    public Optional<Employee> getById(@PathVariable ("employee-id") Long id ) {
        return employeeService.getById(id);
    }









}
