package com.example.module3_exam.model;

import java.time.LocalDate;

public class Staff {
    private int id;
    private String name;
    private LocalDate dateofBirth;
    private String address;

    public Staff(int id, String name, LocalDate dateofBirth, String address) {
        this.id = id;
        this.name = name;
        this.dateofBirth = dateofBirth;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(LocalDate dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateofBirth=" + dateofBirth +
                ", address='" + address + '\'' +
                '}';
    }
}
