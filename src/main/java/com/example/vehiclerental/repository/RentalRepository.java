package com.example.vehiclerental.repository;

import com.example.vehiclerental.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
