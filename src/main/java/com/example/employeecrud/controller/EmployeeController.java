package com.example.employeecrud.controller;

import com.example.employeecrud.model.Employee;
import com.example.employeecrud.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EmployeeController {

    private static final List<String> DEPARTMENTS =
            List.of("Engineering", "Human Resources", "Finance", "Marketing", "Sales", "Operations");

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/employees";
    }

    // READ (list + search)
    @GetMapping("/employees")
    public String list(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees(keyword));
        model.addAttribute("keyword", keyword);
        return "employees/list";
    }

    // CREATE (show form) - declared before /{id} for clarity
    @GetMapping("/employees/new")
    public String createForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", DEPARTMENTS);
        model.addAttribute("pageTitle", "Add Employee");
        return "employees/form";
    }

    // READ (single)
    @GetMapping("/employees/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "employees/view";
    }

    // UPDATE (show form)
    @GetMapping("/employees/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        model.addAttribute("departments", DEPARTMENTS);
        model.addAttribute("pageTitle", "Edit Employee");
        return "employees/form";
    }

    // CREATE + UPDATE (submit)
    @PostMapping("/employees/save")
    public String save(@Valid @ModelAttribute("employee") Employee employee,
                       BindingResult result,
                       Model model,
                       RedirectAttributes redirectAttributes) {

        if (employee.getEmail() != null
                && employeeService.isEmailTaken(employee.getEmail(), employee.getId())) {
            result.rejectValue("email", "duplicate", "This email is already in use");
        }

        if (result.hasErrors()) {
            model.addAttribute("departments", DEPARTMENTS);
            model.addAttribute("pageTitle", employee.getId() == null ? "Add Employee" : "Edit Employee");
            return "employees/form";
        }

        boolean isNew = employee.getId() == null;
        employeeService.saveEmployee(employee);
        redirectAttributes.addFlashAttribute("success",
                "Employee " + (isNew ? "created" : "updated") + " successfully!");
        return "redirect:/employees";
    }

    // DELETE
    @PostMapping("/employees/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        employeeService.deleteEmployee(id);
        redirectAttributes.addFlashAttribute("success", "Employee deleted successfully!");
        return "redirect:/employees";
    }
}
