package com.example.springcrud;

import com.example.springcrud.model.Employee;
import com.example.springcrud.repository.EmployeeRepo;
import com.example.springcrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService service


    @RequestMapping("/list")
    private String viewHomePage(Model model) {
        List<Employee> employees = e.findAll();
        model.addAttribute("employees", employees);
        return "employee-list";
    }

    @GetMapping("/create")
    private String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }

    @PostMapping("/create")
    private String createEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employee-form";
        }
        employeeRepository.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    private String showEditForm(@PathVariable("id") long id, Model model) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee id: " + id));

        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @PostMapping("/edit/{id}")
    private String updateEmployee(@PathVariable("id") long id,@Valid @ModelAttribute("employee") Employee employee,
                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "employee-form";
        }

        employee.setId(id);
        employeeRepository.save(employee);
        return "redirect:/api/employees/";
    }

    @GetMapping("/delete/{id}")
    private String deleteEmployee(@PathVariable("id") long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee id: " + id));
        employeeRepository.delete(employee);
        return "redirect:/api/employees/";
    }
