package com.example.ReVueltaBack.repositorios;

import com.example.ReVueltaBack.modelos.ImagenesPrendas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImagenesPrendaRepository extends JpaRepository<ImagenesPrendas, UUID> {

    List<ImagenesPrendas> findByPrendasIdOrderByOrdenAsc(UUID prendaId);
    long countByPrendasId(UUID prendaId);
    List<ImagenesPrendas> findByFormato(String formato);

    List<ImagenesPrendas> findByFecha_subidaGreaterThanEqual(java.time.LocalDate fecha);
    @Query("SELECT ip FROM ImagenesPrendas ip " +
           "WHERE ip.prendas.id = :prendaId " +
           "AND ip.es_Principal = true")
    Optional<ImagenesPrendas> obtenerImagenPrincipal(@Param("prendaId") UUID prendaId);

    @Query("SELECT ip FROM ImagenesPrendas ip " +
           "WHERE ip.prendas.id = :prendaId " +
           "AND ip.es_Principal = false " +
           "ORDER BY ip.orden ASC")
    List<ImagenesPrendas> obtenerImagenesSecundarias(@Param("prendaId") UUID prendaId);
}