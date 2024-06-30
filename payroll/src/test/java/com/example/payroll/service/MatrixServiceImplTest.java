package com.example.payroll.service;

import com.example.payroll.model.MatrixSalary;
import com.example.payroll.repository.MatrixRepository;
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

public class MatrixServiceImplTest {

    @Mock
    private MatrixRepository matrixRepository;

    @InjectMocks
    private MatrixServiceImpl matrixService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveMatrix() {
        // Given
        MatrixSalary matrixSalary = new MatrixSalary();
        matrixSalary.setId(1L);
        matrixSalary.setGrade(3);
        when(matrixRepository.save(any(MatrixSalary.class))).thenReturn(matrixSalary);

        // When
        MatrixSalary savedMatrix = matrixService.saveMatrix(matrixSalary);

        // Then
        assertEquals(1L, savedMatrix.getId());
        assertEquals(3, savedMatrix.getGrade());
        verify(matrixRepository, times(1)).save(any(MatrixSalary.class));
    }

    @Test
    public void testGetAllMatrix() {
        // Given
        List<MatrixSalary> matrices = Arrays.asList(
                new MatrixSalary(1L, 3, 2000.0f, 100.0f, 300.0f, 150.0f, null),
                new MatrixSalary(2L, 4, 2200.0f, 120.0f, 350.0f, 160.0f, null)
        );
        when(matrixRepository.findAll()).thenReturn(matrices);

        // When
        List<MatrixSalary> retrievedMatrices = matrixService.getAllMatrix();

        // Then
        assertEquals(2, retrievedMatrices.size());
        assertEquals(3, retrievedMatrices.get(0).getGrade());
        assertEquals(4, retrievedMatrices.get(1).getGrade());
        verify(matrixRepository, times(1)).findAll();
    }

    @Test
    public void testGetMatrixById() {
        // Given
        Long id = 1L;
        MatrixSalary matrixSalary = new MatrixSalary();
        matrixSalary.setId(id);
        matrixSalary.setGrade(3);
        when(matrixRepository.findById(id)).thenReturn(Optional.of(matrixSalary));

        // When
        MatrixSalary foundMatrix = matrixService.getMatrixById(id);

        // Then
        assertEquals(1L, foundMatrix.getId());
        assertEquals(3, foundMatrix.getGrade());
        verify(matrixRepository, times(1)).findById(id);
    }

    @Test
    public void testGetMatrixByGrade() {
        // Given
        int grade = 3;
        MatrixSalary matrixSalary = new MatrixSalary();
        matrixSalary.setId(1L);
        matrixSalary.setGrade(grade);
        when(matrixRepository.findByGrade(grade)).thenReturn(Optional.of(matrixSalary));

        // When
        MatrixSalary foundMatrix = matrixService.getMatrixByGrade(grade);

        // Then
        assertEquals(1L, foundMatrix.getId());
        assertEquals(grade, foundMatrix.getGrade());
        verify(matrixRepository, times(1)).findByGrade(grade);
    }
}
