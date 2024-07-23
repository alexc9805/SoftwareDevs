package com.softwaredevs.proyecto.controllers;

import com.softwaredevs.proyecto.entities.Employee;
import com.softwaredevs.proyecto.entities.Enterprise;
import com.softwaredevs.proyecto.entities.Transaction;
import com.softwaredevs.proyecto.services.EmployeeService;
import com.softwaredevs.proyecto.services.EnterpriseService;
import com.softwaredevs.proyecto.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.PostUpdate;
import java.util.List;

@Controller
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    EnterpriseService enterpriseService;




    @GetMapping("/transactions")
    public String getTransactions(Model model , @AuthenticationPrincipal OidcUser principal) {
        model.addAttribute("transacciones", transactionService.getTransactionList());
        model.addAttribute("total", transactionService.sumarTotal());
        if(principal!=null){
            Employee employee=this.employeeService.getEmployee(principal.getClaims());
            model.addAttribute("user",employee);
        }else{
            model.addAttribute("user",null);
        }
        return "transactions";
    }


    @GetMapping("/transactions/add")
    public String addTransaction(Model model, @AuthenticationPrincipal OidcUser principal){
    Transaction transaccion =new Transaction();
    model.addAttribute("transaccion", transaccion);
    List<Employee> employeeToModel =employeeService.getEmployeeList();
    model.addAttribute("employeeToModel", employeeToModel);
    List<Enterprise> enterpriseToModel =enterpriseService.getEnterpriseList();
    model.addAttribute("enterpriseToModel", enterpriseToModel);
        if(principal!=null){
            Employee employee=this.employeeService.getEmployee(principal.getClaims());
            model.addAttribute("user",employee);
        }else{
            model.addAttribute("user",null);
        }
        return "new-transaction";
    }

    @GetMapping("/transactions/{id}")
    public String getTransaction(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal OidcUser principal) {
        model.addAttribute("transaccion", this.transactionService.getTransactionId(id));
        List<Employee> employeeToModel =employeeService.getEmployeeList();
        model.addAttribute("employeeToModel", employeeToModel);
        List<Enterprise> enterpriseToModel =enterpriseService.getEnterpriseList();
        model.addAttribute("enterpriseToModel", enterpriseToModel);
        if(principal!=null){
            Employee employee=this.employeeService.getEmployee(principal.getClaims());
            model.addAttribute("user",employee);
        }else{
            model.addAttribute("user",null);
        }
        return "edit-transaction";
    }

    @PostMapping("/transaction")
    private RedirectView createTransaction(@ModelAttribute @DateTimeFormat(pattern = "YYYY-MM-DD") Transaction transaction){
    transactionService.createTransaction(transaction);
    return new RedirectView("/transactions");

    }

     @DeleteMapping("/transactions/{id}")
    public RedirectView removeTransaction(@PathVariable("id") Long id) {
        this.transactionService.deleteTransaction(id);
        return new RedirectView("/transactions");
    }

    @PatchMapping("/transactions/{id}")
    public RedirectView modifyTransaction(@PathVariable("id") Long id, @ModelAttribute @DateTimeFormat(pattern = "YYYY-MM-DD") Transaction transaction, Model model) {
        this.transactionService.modifyTransaction(id, transaction);
        return new RedirectView("/transactions");
    }
}
