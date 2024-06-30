package com.example.payroll.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "MATRIX")
public class MatrixSalary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int grade;
    private float basicSalary;
    private float payCut;
    private float allowance;

    private float headOfFamily;

    @OneToOne
    @JoinColumn(name = "payroll_id")
    @JsonIgnore
    private Payroll payroll;
}
