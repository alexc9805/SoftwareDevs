package com.softwaredevs.proyecto.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "concept")
    private String concept;
    @Column(name = "amount")
    private float amount;


    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Employee.class)
    @JoinColumn(name = "employee_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Enterprise.class)
    @JoinColumn(name = "enterprise_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Enterprise enterprise;

    @Column(name = "createAt")
    private LocalDate createAt;
    @Column(name = "updateAt")
    private LocalDate updateAt;

    public Transaction() {
    }

    public Transaction(String concept, float amount, Employee employee, Enterprise enterprise) {
        this.concept = concept;
        this.amount = amount;
        this.employee = employee;
        this.enterprise = enterprise;
        this.createAt=LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public LocalDate getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDate updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", concept='" + concept + '\'' +
                ", amount=" + amount +
                ", employee=" + employee +
                ", enterprise=" + enterprise +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}

