package com.example.ReVueltaBack.repositorios;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ReVueltaBack.modelos.PuntoAcopio;

@Repository
public interface IPuntoAcopioRepositorio extends JpaRepository<PuntoAcopio, UUID> {

    List<PuntoAcopio> findByNombreContainingIgnoreCase(String nombre);

    List<PuntoAcopio> findByActivoTrue();

    @Query("SELECT p FROM PuntoAcopio p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :texto, '%'))")
    List<PuntoAcopio> buscarPorNombre(@Param("texto") String texto);
}
