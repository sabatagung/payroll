package com.example.payroll.repository;

import com.example.payroll.model.MatrixSalary;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MatrixSalaryRepositoryTest {
    @Autowired
    MatrixRepository matrixRepository;

    @Test
    public void testCreateMatrixSalary(){
        //arrange
        MatrixSalary matrixSalary = MatrixSalary.builder()
                .grade(1)
                .basicSalary(5000000)
                .payCut(100000)
                .allowance(150000)
                .headOfFamily(200000)
                .build();
        //act
        MatrixSalary createMatrixSalary = matrixRepository.save(matrixSalary);

        //assert
        Assertions.assertThat(createMatrixSalary).isNotNull();
        Assertions.assertThat(createMatrixSalary.getId()).isGreaterThan(0);
    }
}
