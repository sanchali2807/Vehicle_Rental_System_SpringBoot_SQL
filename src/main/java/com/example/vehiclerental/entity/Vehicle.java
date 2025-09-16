package com.example.vehiclerental.entity;

import jakarta.persistence.*;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;  
    private String type;
    private boolean rented;

    public Vehicle() {}  


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public boolean isRented() { return rented; }
    public void setRented(boolean rented) { this.rented = rented; }
}
