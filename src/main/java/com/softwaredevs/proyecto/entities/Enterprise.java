package com.softwaredevs.proyecto.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="enterprise")
public class Enterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "document")
    private String document;
    //TODO arraylist users and transactions
    @OneToMany(mappedBy = "enterprise",cascade = CascadeType.ALL)
    private List<Employee> employees;
    @OneToMany(mappedBy = "enterprise",cascade = CascadeType.ALL)
    private List<Transaction> transactions;
    @Column(name="createAt")
    private LocalDate createAt;
    @Column(name = "updateAt")
    private LocalDate updateAt;
    public Enterprise(){

    }
    public Enterprise(Long id,String name, String address, String phone, String document){
        this.id=id;
        this.name=name;
        this.address=address;
        this.phone=phone;
        this.document=document;
        this.createAt=LocalDate.now();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
    public void setUpdateAt(LocalDate date){
        this.updateAt=date;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public LocalDate getUpdateAt(){
        return updateAt;
    }
    public LocalDate getCreateAt(){
        return createAt;
    }

    // -----TO STRING----//

    @Override
    public String toString() {
        return "Enterprise: " +
                "\n name='" + name + '\'' +
                "\n document='" + document + '\'' +
                "\n phone='" + phone + '\'' +
                "\n address='" + address + '\'' +
                "\n createAt=" + createAt;
    }
}
