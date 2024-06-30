package com.example.payroll.service;

import com.example.payroll.model.MatrixSalary;

import java.util.List;

public interface MatrixService {
    MatrixSalary saveMatrix(MatrixSalary matrixSalary);
    MatrixSalary getMatrixById(Long id);
    List<MatrixSalary> getAllMatrix ();
    MatrixSalary getMatrixByGrade (int grade);

}
