package com.springcrud.mvc.controllers;

import com.springcrud.mvc.models.Employee;
import com.springcrud.mvc.services.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    //Display Employee List
    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("listEmployees",employeeService.getAllEmployees());
        return "index";
    }

    @GetMapping("/newEmployee")
    public String showNewEmployeeForm(Model model){
        //Create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        //save employee to db
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/updateEmployee/{id}")
    public String updateEmployee(@PathVariable(value = "id") Long id,Model model){
        //get employee from the service
        Employee employee = employeeService.getEmployeeById(id);

        //Set employee as model attribute to populate the form
        model.addAttribute("employee",employee);
        return "update_employee";
    }

    @PostMapping("/updateEmployeeSave/{id}")
    public String updateEmployeeFinalize(@ModelAttribute("employee") Employee employee,@PathVariable(value = "id") Long id) {
        Employee currentInstance = employeeService.getEmployeeById(id);
        currentInstance.setFirstName(employee.getFirstName());
        currentInstance.setLastName(employee.getLastName());
        currentInstance.setEmail(employee.getEmail());
        employeeService.saveEmployee(currentInstance);
        return "redirect:/";
    }

    @PostMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id){
        employeeService.deleteEmployee(id);
        return "redirect:/";
    }
}
