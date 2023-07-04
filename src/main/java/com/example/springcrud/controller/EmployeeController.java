package com.example.springcrud.controller;

import com.example.springcrud.model.Employee;
import com.example.springcrud.repository.EmployeeRepo;
import com.example.springcrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService service;


    @RequestMapping("/employee-list")
    private String viewHomePage(Model model) {
        List<Employee> employees = service.listEmployee();
        model.addAttribute("employees", employees);
        return "employee-list";
    }

    @RequestMapping("/new")
    private String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    private String saveEmployee(@ModelAttribute("employee") Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employee-form";
        }
        service.saveEmployee(employee);
        return "redirect:/employee-list";
    }

    @RequestMapping("/edit/{id}")
    private ModelAndView showEditEmployeeForm(@PathVariable(name = "id") long id) {
        ModelAndView mav = new ModelAndView("employee-edit");
        Employee employee = service.getEmployee(id);
        mav.addObject("employee", employee);
        return mav;
    }
    @RequestMapping("/delete/{id}")
    private String deleteEmployee(@PathVariable("id") long id) {
        Employee employee = service.getEmployee(id);
        service.deleteEmployee(id);
        return "redirect:/employee-list";
    }
}