package com.example.ReVueltaBack.repositorios;

import com.example.ReVueltaBack.modelos.ImagenesPrenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImagenesPrendaRepository extends JpaRepository<ImagenesPrenda, UUID> {

    List<ImagenesPrenda> findByPrendaIdOrderByOrdenAsc(UUID prendaId);
    long countByPrendaId(UUID prendaId);
    List<ImagenesPrenda> findByFormato(String formato);

    List<ImagenesPrenda> findByFecha_subidaGreaterThanEqual(java.time.LocalDate fecha);

    @Query("SELECT ip FROM ImagenesPrenda ip " +
           "WHERE ip.prenda.id = :prendaId " +
           "AND ip.es_Principal = true")
    Optional<ImagenesPrenda> obtenerImagenPrincipal(@Param("prendaId") UUID prendaId);

    @Query("SELECT ip FROM ImagenesPrenda ip " +
           "WHERE ip.prenda.id = :prendaId " +
           "AND ip.es_Principal = false " +
           "ORDER BY ip.orden ASC")
    List<ImagenesPrenda> obtenerImagenesSecundarias(@Param("prendaId") UUID prendaId);
}
