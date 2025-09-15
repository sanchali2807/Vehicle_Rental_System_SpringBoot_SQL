package com.example.vehiclerental.controller;

import com.example.vehiclerental.entity.Vehicle;
import com.example.vehiclerental.service.VehicleService;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService service;
     
    public VehicleController(VehicleService service) {
        this.service = service;
    }

    // @PostMapping
    // public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
    //     return service.addVehicle(vehicle);
    // }
    @PostMapping
public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
    try {
        return service.addVehicle(vehicle);
    } catch (Exception e) {
        e.printStackTrace(); // see why it’s failing
        throw e; // or return ResponseEntity.status(500).build();
    }
}


    @GetMapping
    public List<Vehicle> listVehicles() {
        return service.listVehicles();
    }

    @DeleteMapping("/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        service.deleteVehicle(id);
        return "Vehicle deleted with ID: " + id;
    }

    @PutMapping("/{id}/rent")
    public Vehicle rentVehicle(@PathVariable Long id) {
        return service.rentVehicle(id);
    }

    @PutMapping("/{id}/return")
    public Vehicle returnVehicle(@PathVariable Long id) {
        return service.returnVehicle(id);
    }
}
