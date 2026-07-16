package com.example.ReVueltaBack.repositorios;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ReVueltaBack.modelos.Campana;



@Repository
public interface ICampanaRepositorio extends JpaRepository<Campana, UUID> {


    
    // Consultas personalizadas con JPQL (todas habilidatas)

    @Query("SELECT c FROM Campana c " +
    "WHERE LOWER(c.descripcion_campana) = LOWER(:descripcion_campana)")
    List<Campana> buscarPorDescripcion_Campana(String descripcion_campana);

    @Query("SELECT c FROM Campana c " +
    "WHERE LOWER(c.nombre_campana) = LOWER(:nombre_campana)")
    List<Campana> buscarPorNombre_Campana(String nombre_campana);

    @Query("SELECT c FROM Campana c WHERE c.fecha_inicio = :fecha_inicio")
    List<Campana> buscarPorFecha_inicio(@Param("fecha_inicio") LocalDateTime fecha_inicio); 

    @Query("SELECT c FROM Campana c WHERE c.fecha_inicio = :fecha_final")
    List<Campana> buscarPorFecha_final(@Param("fecha_inicio") LocalDateTime fecha_final);

    @Query("SELECT c FROM Campana c " +
    "WHERE LOWER(c.descuento_pct) = LOWER(:descuento_pct)")
    List<Campana> buscarPorDescuento_pct(String descuento_pct);

    @Query("SELECT c FROM Campana c " +
    "WHERE LOWER(c.activa) = LOWER(:activa)")
    List<Campana> buscarPorActiva(String activa);


}
