package com.softwaredevs.proyecto.services;

import com.softwaredevs.proyecto.entities.Employee;
import com.softwaredevs.proyecto.entities.Enterprise;
import com.softwaredevs.proyecto.entities.Transaction;
import com.softwaredevs.proyecto.repositories.EmployeeRepository;
import com.softwaredevs.proyecto.repositories.EnterpriseRepository;
import com.softwaredevs.proyecto.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired  //injección de dependiencia
    TransactionRepository transactionRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EnterpriseRepository enterpriseRepository;



    //Crear un movimiento de dinero
    public String createTransaction(Transaction transaction){
        Optional<Enterprise> dbEnterprise = this.enterpriseRepository.findById(transaction.getEnterprise().getId());
        Optional<Employee> dbEmployee = this.employeeRepository.findById(transaction.getEmployee().getId());
        if (!dbEnterprise.isPresent())
            return "El Id de la empresa no es valido";
        if (!dbEmployee.isPresent())
            return "El Id del empleado no es valido";
        transaction.setEnterprise(dbEnterprise.get());
        transaction.setEmployee(dbEmployee.get());

        transaction.setCreateAt(LocalDate.now());
        this.transactionRepository.save(transaction);
        return "Transacción creada con exito";
    }


    //Encontrar todos los movimientos de dinero
    public List<Transaction> getTransactionList(){ return this.transactionRepository.findAll();
    }
    //Encontrar movimientos de dinero por ID
    public Transaction getTransactionId(long id) {
        Optional<Transaction> transaction = this.transactionRepository.findById(id);
        return transaction.orElse(null);
    }
    public boolean deleteTransaction(long id){
        try {
            this.transactionRepository.deleteById(id);
            return true;
        }catch ( Exception ex){
            return false;
        }
    }

    public String modifyTransaction(long id, Transaction transaction){
        Optional<Transaction> dbTransaction=this.transactionRepository.findById(id);
        Optional<Enterprise> dbEnterprise = this.enterpriseRepository.findById(transaction.getEnterprise().getId());
        Optional<Employee> dbEmployee = this.employeeRepository.findById(transaction.getEmployee().getId());
        if (!dbEnterprise.isPresent())
            return "El Id de la empresa no es valido";
        if (!dbEmployee.isPresent())
            return "El Id del empleado no es valido";

        if(dbTransaction.isPresent()){
            Transaction e = dbTransaction.get();
            e=transaction;
            e.setId(dbTransaction.get().getId());
            e.setCreateAt(dbTransaction.get().getCreateAt());
            e.setUpdateAt(LocalDate.now());
            e.setEnterprise(dbEnterprise.get());
            e.setEmployee(dbEmployee.get());
            this.transactionRepository.save(e);
            return "Transacción modificada con éxito.";
        }
        return "No se pudo modificar la Transacción";
    }

    public float sumarTotal() {
        List<Transaction> transactions = getTransactionList();
        float cantidad = 0;
        for (int i = 0; i < transactions.size(); i++) {
            Transaction tx = transactions.get(i);
            cantidad = cantidad + tx.getAmount();

        }
        return cantidad;
    }
}
