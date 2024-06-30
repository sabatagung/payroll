package com.example.payroll.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayrollResponseDTO {
    private Long id;
    private float basicSalary;
    private float payCut;
    private float additionalSalary;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date date;
    private int dayOfPresent;
    private int dayOfAbsence;
    private float totalSalary;
    private float headOfFamily;

    private EmployeeDTO employee;
}
