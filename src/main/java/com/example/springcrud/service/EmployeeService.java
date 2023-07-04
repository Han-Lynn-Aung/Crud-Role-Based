package com.example.springcrud.service;

import com.example.springcrud.model.Employee;
import com.example.springcrud.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public List<Employee> listEmployee(){
       return employeeRepo.findAll();
    }

    public void saveEmployee(Employee employee){
        employeeRepo.save(employee);
    }

    public Employee getEmployee(long id){
      return employeeRepo.findById(id).get();
    }

    public void deleteEmployee(long id){
        employeeRepo.deleteById(id);
    }
}
