package com.example.payroll.service;

import com.example.payroll.dto.EmployeeDTO;
import com.example.payroll.model.Employee;
import com.example.payroll.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee create(Employee employee) {
        logger.info("Creating employee: {}", employee);
        return employeeRepository.save(employee);
    }

    public List<Employee> getAll() {
        logger.info("Fetching all employees");
        return employeeRepository.findAll();
    }

    public Optional<Employee> getById(Long id) {
        logger.info("Fetching employee by id: {}", id);
        return employeeRepository.findById(id);
    }

    public List<EmployeeDTO> getAllEmployees() {
        logger.info("Fetching all employees with DTO mapping");
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }
}
