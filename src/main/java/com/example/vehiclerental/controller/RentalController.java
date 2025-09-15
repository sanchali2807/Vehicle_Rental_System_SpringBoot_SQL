package com.example.vehiclerental.controller;

import com.example.vehiclerental.entity.Rental;
import com.example.vehiclerental.repository.RentalRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalRepository rentalRepository;

    public RentalController(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @PostMapping
    public Rental createRental(@RequestBody Rental rental) {
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
