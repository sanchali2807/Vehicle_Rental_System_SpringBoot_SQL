package com.example.vehiclerental.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long vehicleId;
    private String customerName;
    private LocalDate rentalDate;
    private LocalDate returnDate;

    public Rental() {}

    public Rental(Long vehicleId, String customerName, LocalDate rentalDate, LocalDate returnDate) {
        this.vehicleId = vehicleId;
        this.customerName = customerName;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getVehicleId() { return vehicleId; }
    public void setVehicleId(Long vehicleId) { this.vehicleId = vehicleId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public LocalDate getRentalDate() { return rentalDate; }
    public void setRentalDate(LocalDate rentalDate) { this.rentalDate = rentalDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
}
