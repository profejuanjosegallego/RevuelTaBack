package com.example.ReVueltaBack.repositorios;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ReVueltaBack.modelos.Transportista;

@Repository
public interface TransportistasRepository extends JpaRepository<Transportista, UUID> {

}