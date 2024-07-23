package com.softwaredevs.proyecto.controllers;

import com.softwaredevs.proyecto.entities.Employee;
import com.softwaredevs.proyecto.entities.Enterprise;
import com.softwaredevs.proyecto.entities.Profile;
import com.softwaredevs.proyecto.entities.Transaction;
import com.softwaredevs.proyecto.services.EmployeeService;
import com.softwaredevs.proyecto.services.EnterpriseService;
import com.softwaredevs.proyecto.services.ProfileService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapAutoConfiguration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class
EmployeeController {
    // ATRIBUTOS
    @Autowired
    EmployeeService employeeService;

    @Autowired
    EnterpriseService enterpriseService;
    @Autowired
    ProfileService profileService;

    // Controlador para mostrar todos los empleados
    @GetMapping("/employees")
    public String getEmployees(Model model, @AuthenticationPrincipal OidcUser principal) {
        if(principal!=null){
            Employee employee=this.employeeService.getEmployee(principal.getClaims());
            model.addAttribute("user",employee);

            List<Employee> listEmployees = this.employeeService.getEmployeeList();
            model.addAttribute("listEmployees",listEmployees);
        }else{
            model.addAttribute("user",null);
        }
        return "employee/list-employee";
    }



    @GetMapping("/employees/add")
    public String addEmployee(Model model, @AuthenticationPrincipal OidcUser principal){
        if(principal!=null) {
            Employee principalemployee = this.employeeService.getEmployee(principal.getClaims());
            model.addAttribute("user", principalemployee );

            Employee employee = new Employee();
            Profile profile = new Profile();
            List<Enterprise> enterpriseToModel =enterpriseService.getEnterpriseList();
            model.addAttribute("employeeModel", employee);
            model.addAttribute("profileModel", profile);
            model.addAttribute("enterpriseModel", enterpriseToModel);

            }else{
            model.addAttribute("user",null);
            }
        return "employee/new-employee";
    }


    //Gardar un nuevo empleado, este es el que conecta cuando hundimos el boton guardar
    @PostMapping("/employee")
    private RedirectView createEmployee(@ModelAttribute @DateTimeFormat(pattern = "YYYY-MM-DD") Employee employee, @ModelAttribute("profileModel") Profile profile, @ModelAttribute("enterpriseModel") Enterprise enterprise){
        employeeService.createEmployee(employee);
        profile.setEmployee(employee);
        profileService.crearProfile(profile);
        return new RedirectView("/employees");

    }


    // metodo para eliminar
    @DeleteMapping("/employee/{id}")
    public RedirectView removeEmployee(@PathVariable("id") Long id) {
        this.employeeService.removeEmployee(id);
        return new RedirectView("/employees");
    }

    // metodo para llamar al formulario de editar de empleado
    @GetMapping("/employee/{id}")
    public String viewFormEditEmployee(@PathVariable("id") Long id, Model model,  @AuthenticationPrincipal OidcUser principal){
        if(principal!=null){
            Employee employee=this.employeeService.getEmployee(principal.getClaims());
            model.addAttribute("user",employee);

            Employee employeeModel = employeeService.getEmployeeId(id);
            Profile profileEmployeeModel = employeeModel.getProfile();
            model.addAttribute("employeeModel", employeeModel);
            model.addAttribute("profileEmployeeModel", profileEmployeeModel);
        }else{
            model.addAttribute("user",null);
        }
        return "employee/edit-employee";
    }


    // Metodo para actualizar
    @PatchMapping("/user/{id}")
    public RedirectView modificarEmployee (@PathVariable("id") Long id, @ModelAttribute("employeeModel") Employee employee, @ModelAttribute("profileEmployeeModel") Profile profile) {
        employeeService.modifyEmployee(id, employee, profile);
        return new RedirectView("/employees");
    }
}
