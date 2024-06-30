package com.example.payroll.dto;

import com.example.payroll.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String name;
    private String gender;
    private int grade;
    private Boolean isMarried;

    // map from Employee entity to EmployeeDTO
    public static EmployeeDTO fromEmployee(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setGender(employee.getGender());
        dto.setGrade(employee.getGrade());
        dto.setIsMarried(employee.getIsMarried());
        return dto;
    }
}
