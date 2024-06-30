package com.example.payroll.controller;

import com.example.payroll.dto.EmployeeDTO;
import com.example.payroll.model.Employee;
import com.example.payroll.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateEmployee() {
        // Given
        Employee request = new Employee();
        request.setName("John Doe");

        Employee createdEmployee = new Employee();
        createdEmployee.setId(1L);
        createdEmployee.setName("John Doe");

        when(employeeService.create(any(Employee.class))).thenReturn(createdEmployee);

        // When
        ResponseEntity<Employee> responseEntity = (ResponseEntity<Employee>) employeeController.createEmployee(request);

        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(1L, responseEntity.getBody().getId());
        assertEquals("John Doe", responseEntity.getBody().getName());
        verify(employeeService, times(1)).create(any(Employee.class));
    }

    @Test
    public void testGetAllEmployees() {
        // Given
        EmployeeDTO employeeDTO1 = new EmployeeDTO();
        employeeDTO1.setId(1L);
        employeeDTO1.setName("John Doe");

        EmployeeDTO employeeDTO2 = new EmployeeDTO();
        employeeDTO2.setId(2L);
        employeeDTO2.setName("Jane Smith");

        List<EmployeeDTO> employees = Arrays.asList(employeeDTO1, employeeDTO2);

        when(employeeService.getAllEmployees()).thenReturn(employees);

        // When
        List<EmployeeDTO> returnedEmployees = employeeController.getAll();

        // Then
        assertEquals(2, returnedEmployees.size());
        assertEquals(1L, returnedEmployees.get(0).getId());
        assertEquals("John Doe", returnedEmployees.get(0).getName());
        assertEquals(2L, returnedEmployees.get(1).getId());
        assertEquals("Jane Smith", returnedEmployees.get(1).getName());
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    public void testGetEmployeeById() {
        // Given
        Long employeeId = 1L;
        Employee employee = new Employee();
        employee.setId(employeeId);
        employee.setName("John Doe");

        when(employeeService.getById(employeeId)).thenReturn(Optional.of(employee));

        // When
        ResponseEntity<Employee> returnedEmployee = employeeController.getById(employeeId);

        // Then
        assertEquals(employeeId, returnedEmployee.getBody().getId());
        assertEquals("John Doe", returnedEmployee.getBody().getName());
        verify(employeeService, times(1)).getById(employeeId);
    }
}
