package com.example.employeecrud.service;

import com.example.employeecrud.exception.ResourceNotFoundException;
import com.example.employeecrud.model.Employee;
import com.example.employeecrud.repository.EmployeeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return repository.findAll(Sort.by("id"));
        }
        return repository.search(keyword.trim());
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found with id " + id);
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isEmailTaken(String email, Long currentId) {
        return repository.findByEmailIgnoreCase(email)
                .map(existing -> !existing.getId().equals(currentId))
                .orElse(false);
    }
}
