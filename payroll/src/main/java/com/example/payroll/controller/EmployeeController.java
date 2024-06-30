package com.example.payroll.controller;

import com.example.payroll.dto.EmployeeDTO;
import com.example.payroll.model.Employee;
import com.example.payroll.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public HttpEntity<Employee> createEmployee(@RequestBody Employee request) {
        logger.info("Received request to create employee: {}", request);
        Employee response = employeeService.create(request);
        logger.info("Created employee with id: {}", response.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public List<EmployeeDTO> getAll() {
        logger.info("Fetching all employees");
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employee-id}")
    public ResponseEntity<Employee> getById(@PathVariable("employee-id") Long id) {
        logger.info("Fetching employee with id: {}", id);
        Optional<Employee> employeeOptional = employeeService.getById(id);
        if (employeeOptional.isPresent()) {
            return ResponseEntity.ok(employeeOptional.get());
        } else {
            logger.warn("Employee with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }
}

