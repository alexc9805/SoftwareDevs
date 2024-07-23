package com.softwaredevs.proyecto.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "Employees")
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String name;
    private String email;
    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "employee")
    private Profile profile;
    @Enumerated(EnumType.STRING)
    private Enum_RoleName role;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Enterprise.class)
    @JoinColumn(name = "id_enterprise")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Enterprise enterprise;
    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private List<Transaction> transactions;
    private LocalDate updateAt;
    private LocalDate createdAt;

    public Employee() {
    }

    public Employee(String name, String email, Enum_RoleName role, Enterprise enterprise, Profile profile) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.enterprise = enterprise;
        this.createdAt = LocalDate.now();
        this.profile = profile;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Enum_RoleName getRole() {
        return role;
    }

    public void setRole(Enum_RoleName role) {
        this.role = role;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public LocalDate getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDate updateAt) {
        this.updateAt = updateAt;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "Employee: " +
                "\n name='" + name + '\'' +
                "\n email='" + email + '\'' +
                "\n role=" + role + "\n" +
                "\n" + enterprise + "\n" +
                "\n createdAt=" + createdAt +
                '}';
    }
}
