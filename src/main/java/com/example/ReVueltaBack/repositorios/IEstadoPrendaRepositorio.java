package com.example.ReVueltaBack.repositorios;

import com.example.ReVueltaBack.modelos.EstadoPrenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface IEstadoPrendaRepositorio extends JpaRepository<EstadoPrenda, UUID> {

    List<EstadoPrenda> findByNombreContainingIgnoreCase(String nombre);

    List<EstadoPrenda> findByRequiereRevisionTrue();

    @Query("SELECT e FROM EstadoPrenda e WHERE LOWER(e.nombre) LIKE LOWER(CONCAT('%', :texto, '%'))")
    List<EstadoPrenda> buscarPorNombre(@Param("texto") String texto);
}