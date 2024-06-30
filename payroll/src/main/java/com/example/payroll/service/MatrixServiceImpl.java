package com.example.payroll.service;

import com.example.payroll.model.MatrixSalary;
import com.example.payroll.repository.MatrixRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MatrixServiceImpl implements MatrixService {

    @Autowired
    private MatrixRepository matrixRepository;

    public MatrixSalary saveMatrix(MatrixSalary matrixSalary){
        return matrixRepository.save(matrixSalary);
    }

    public List<MatrixSalary> getAllMatrix (){
        return matrixRepository.findAll();
    }


    @Override
    public MatrixSalary getMatrixById(Long id) {
        return matrixRepository.findById(id).orElse(null);
    }

    @Override
    public MatrixSalary getMatrixByGrade(int grade) {
        return matrixRepository.findByGrade(grade).orElse(null);
    }


}
