package com.softwaredevs.proyecto.entities;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String image;
    private String phone;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    private LocalDate createdAt;
    private LocalDate updateAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Profile() {
    }

    public Profile(String image, String phone, Employee employee){
        this.image=image;
        this.phone=phone;
        this.employee=employee;
        this.createdAt=LocalDate.now();

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}