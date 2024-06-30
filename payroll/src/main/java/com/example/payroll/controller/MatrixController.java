package com.example.payroll.controller;

import com.example.payroll.model.Employee;
import com.example.payroll.model.MatrixSalary;
import com.example.payroll.service.MatrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matrix")
public class MatrixController {

    @Autowired
    private MatrixService matrixService;

    @PostMapping
    public HttpEntity <MatrixSalary> createMatrixSalary(@RequestBody MatrixSalary matrixSalary) {
        MatrixSalary response = matrixService.saveMatrix(matrixSalary);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{matrix-id}")
    public MatrixSalary getMatrixById(@PathVariable ("matrix-id") Long id) {
        return matrixService.getMatrixById(id);
    }

    @GetMapping
    public List<MatrixSalary> getAllMatrix (){
        return matrixService.getAllMatrix();
    }

}
