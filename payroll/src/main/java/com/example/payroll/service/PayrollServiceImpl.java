package com.example.payroll.service;

import com.example.payroll.dto.EmployeeDTO;
import com.example.payroll.dto.PayrollResponseDTO;
import com.example.payroll.model.Employee;
import com.example.payroll.model.MatrixSalary;
import com.example.payroll.model.Payroll;
import com.example.payroll.repository.EmployeeRepository;
import com.example.payroll.repository.MatrixRepository;
import com.example.payroll.repository.PayrollRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PayrollServiceImpl implements PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MatrixRepository matrixRepository;


    @Override
    public PayrollResponseDTO addPayroll(Long employeeId, int dayOfPresent, int dayOfAbsence, Date date) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + employeeId));

        // Calculate the start and end of the month for the given date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        Date endDate = calendar.getTime();

        // Check if payroll already exists for this month
        List<Payroll> existingPayrolls = payrollRepository.findByEmployeeIdAndDateBetween(employeeId, startDate, endDate);
        if (!existingPayrolls.isEmpty()) {
            throw new RuntimeException("Payroll already exists for this employee for the given month.");
        }

        MatrixSalary matrixSalary = matrixRepository.findByGrade(employee.getGrade())
                .orElseThrow(() -> new RuntimeException("Matrix entry not found for grade " + employee.getGrade()));

        float additionalSalary = dayOfPresent * matrixSalary.getAllowance();
        float payCut = dayOfAbsence * matrixSalary.getPayCut();
        float basicSalary = matrixSalary.getBasicSalary();
        float totalSalary = basicSalary + additionalSalary - payCut;

        // Calculate headOfFamily if employee is male and married
        float headOfFamily = 0;
        if ("male".equalsIgnoreCase(employee.getGender()) && employee.getIsMarried()) {
            headOfFamily = matrixSalary.getHeadOfFamily();
            totalSalary += headOfFamily;
        }

        Payroll payroll = new Payroll();
        payroll.setEmployee(employee);
        payroll.setDate(date); // Use the provided date
        payroll.setBasicSalary(basicSalary);
        payroll.setAdditionalSalary(additionalSalary);
        payroll.setPayCut(payCut);
        payroll.setDayOfPresent(dayOfPresent);
        payroll.setDayOfAbsence(dayOfAbsence);
        payroll.setTotalSalary((int) totalSalary);
        payroll.setHeadOfFamily(headOfFamily); // Set headOfFamily

        // Set the matrixSalary association
        payroll.setMatrixSalary(matrixSalary);

        payroll = payrollRepository.save(payroll);

        return mapToDTO(payroll);
    }

    private PayrollResponseDTO mapToDTO(Payroll payroll) {
        PayrollResponseDTO dto = new PayrollResponseDTO();
        dto.setId(payroll.getId());
        dto.setBasicSalary(payroll.getBasicSalary());
        dto.setPayCut(payroll.getPayCut());
        dto.setAdditionalSalary(payroll.getAdditionalSalary());
        dto.setDate(payroll.getDate());
        dto.setHeadOfFamily(payroll.getHeadOfFamily());
        dto.setDayOfPresent(payroll.getDayOfPresent());
        dto.setDayOfAbsence(payroll.getDayOfAbsence());
        dto.setTotalSalary(payroll.getTotalSalary());

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(payroll.getEmployee().getId());
        employeeDTO.setName(payroll.getEmployee().getName());
        employeeDTO.setGender(payroll.getEmployee().getGender());
        employeeDTO.setGrade(payroll.getEmployee().getGrade());
        employeeDTO.setIsMarried(payroll.getEmployee().getIsMarried());

        dto.setEmployee(employeeDTO);

        return dto;
    }

    public Optional<Employee> getById (Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Payroll> getAllPayroll() {
        return payrollRepository.findAll();
    }

    public List<PayrollResponseDTO> getPayrollsByEmployeeId(Long employeeId) {
        List<Payroll> payrolls = payrollRepository.findByEmployeeId(employeeId);
        if (payrolls.isEmpty()) {
            throw new RuntimeException("No payrolls found for employee with id " + employeeId);
        }
        return payrolls.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}

