package com.codecool.backend.service;

import com.codecool.backend.dto.EmployeeDTO;
import com.codecool.backend.model.Employee;
import com.codecool.backend.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> all = employeeRepository.findAll();
        return all.stream().map(e -> new EmployeeDTO(e.getLevel(), e.getPosition(), e.getName(), e.getId())).toList();
    }

    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return null;
        }
        return new EmployeeDTO(employee.getLevel(), employee.getPosition(), employee.getName(), employee.getId());
    }

    public void createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.name());
        employee.setLevel(employeeDTO.level());
        employee.setPosition(employeeDTO.position());
        employeeRepository.save(employee);
    }

    public void updateEmployee(EmployeeDTO employeeDTO, Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return;
        }
        employee.setName(employeeDTO.name());
        employee.setLevel(employeeDTO.level());
        employee.setPosition(employeeDTO.position());
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
