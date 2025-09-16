package com.example.vehiclerental.controller;

import com.example.vehiclerental.entity.Rental;
import com.example.vehiclerental.entity.Vehicle;
import com.example.vehiclerental.repository.RentalRepository;
import com.example.vehiclerental.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalRepository rentalRepository;
    private final VehicleService vehicleService; 

   
    public RentalController(RentalRepository rentalRepository, VehicleService vehicleService) {
        this.rentalRepository = rentalRepository;
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public Rental createRental(@RequestBody Rental rental) {
       
        if (rental.getRentalDate() == null) {
            rental.setRentalDate(LocalDate.now());
        }

        
        Vehicle vehicle = vehicleService.rentVehicle(rental.getVehicleId());

        
        return rentalRepository.save(rental);
    }

    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    @GetMapping("/{id}")
    public Rental getRentalById(@PathVariable Long id) {
        return rentalRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public String deleteRental(@PathVariable Long id) {
        rentalRepository.deleteById(id);
        return "Rental deleted with ID: " + id;
    }
}
