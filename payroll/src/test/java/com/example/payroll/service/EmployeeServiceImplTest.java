package com.example.payroll.service;

import com.example.payroll.dto.EmployeeDTO;
import com.example.payroll.model.Employee;
import com.example.payroll.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateEmployee() {
        // Given
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        // When
        Employee savedEmployee = employeeService.create(employee);

        // Then
        assertEquals(1L, savedEmployee.getId());
        assertEquals("John Doe", savedEmployee.getName());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    public void testGetAllEmployees() {
        // Given
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "John Doe", "Male", 3, true, null),
                new Employee(2L, "Jane Smith", "Female", 4, false, null)
        );
        when(employeeRepository.findAll()).thenReturn(employees);

        // When
        List<EmployeeDTO> employeeDTOs = employeeService.getAllEmployees();

        // Then
        assertEquals(2, employeeDTOs.size());
        assertEquals("John Doe", employeeDTOs.get(0).getName());
        assertEquals("Jane Smith", employeeDTOs.get(1).getName());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void testGetEmployeeById() {
        // Given
        Long id = 1L;
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName("John Doe");
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        // When
        Optional<Employee> foundEmployee = employeeService.getById(id);

        // Then
        assertEquals(true, foundEmployee.isPresent());
        assertEquals("John Doe", foundEmployee.get().getName());
        verify(employeeRepository, times(1)).findById(id);
    }
}
