package com.example.payroll.service;

import com.example.payroll.dto.EmployeeDTO;
import com.example.payroll.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee create (Employee employee);
    List <Employee> getAll();
    Optional<Employee> getById (Long id);
    List<EmployeeDTO> getAllEmployees();
}
