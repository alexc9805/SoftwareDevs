package com.softwaredevs.proyecto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.softwaredevs.proyecto.entities.Employee;
import com.softwaredevs.proyecto.services.EmployeeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

@Controller
public class HomeController {
    EmployeeService employeeService;
    public HomeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }
    @GetMapping("/home")
    public String goHome(Model model, @AuthenticationPrincipal OidcUser principal){
        model.addAttribute("titulo","Bienvenido a Software Devs");
        if(principal!=null){
            Employee employee=this.employeeService.getEmployee(principal.getClaims());
            model.addAttribute("user",employee);
        }else{
            model.addAttribute("user",null);
        }
        return "inicio";
    }
    @GetMapping("/")
    public String registro(Model model, @AuthenticationPrincipal OidcUser principal){
        if(principal!=null){
            Employee employee=this.employeeService.getEmployee(principal.getClaims());
            model.addAttribute("user",employee);
        }else{
            model.addAttribute("user",null);
        }
        return "login";
    }
}
