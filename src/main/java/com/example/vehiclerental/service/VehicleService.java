package com.example.vehiclerental.service;

import com.example.vehiclerental.entity.Vehicle;
import com.example.vehiclerental.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    private final VehicleRepository repo;

    public VehicleService(VehicleRepository repo) {
        this.repo = repo;
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        System.out.println("✅ Added Vehicle: " + vehicle.getModel());
        return repo.save(vehicle);
    }

    public List<Vehicle> listVehicles() {
        System.out.println("📋 Listing all vehicles...");
        return repo.findAll();
    }
    public void deleteVehicle(Long id) {
    if (!repo.existsById(id)) {
        throw new RuntimeException("Vehicle not found with ID: " + id);
    }
    repo.deleteById(id);
}

    public Vehicle rentVehicle(Long id) {
    Vehicle vehicle = repo.findById(id)
        .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + id));
    vehicle.setRented(true);
    return repo.save(vehicle);
}

    public Vehicle returnVehicle(Long id) {
        Optional<Vehicle> v = repo.findById(id);
        if (v.isPresent()) {
            Vehicle vehicle = v.get();
            vehicle.setRented(false);
            System.out.println("🔄 Vehicle returned: " + vehicle.getModel());
            return repo.save(vehicle);
        }
        return null;
    }
}
