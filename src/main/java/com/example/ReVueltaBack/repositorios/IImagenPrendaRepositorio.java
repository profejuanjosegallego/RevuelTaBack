package com.example.ReVueltaBack.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.ImagenPrenda;

@Repository
public interface IImagenPrendaRepositorio extends JpaRepository<ImagenPrenda, UUID> {

    List<ImagenPrenda> findByPrendaIdOrderByOrdenAsc(UUID prendaId);
    long countByPrendaId(UUID prendaId);
    List<ImagenPrenda> findByFormato(String formato);
    @Query("SELECT ip FROM ImagenPrenda ip WHERE ip.fecha_subida >= :fecha")
    List<ImagenPrenda> buscarDesdeFechaSubida(@Param("fecha") java.time.LocalDate fecha);

    @Query("SELECT ip FROM ImagenPrenda ip " +
           "WHERE ip.prenda.id = :prendaId " +
           "AND ip.es_Principal = true")
    Optional<ImagenPrenda> obtenerImagenPrincipal(@Param("prendaId") UUID prendaId);

   
    @Query("SELECT ip FROM ImagenPrenda ip " +
           "WHERE ip.prenda.id = :prendaId " +
           "AND ip.es_Principal = false " +
           "ORDER BY ip.orden ASC")
    List<ImagenPrenda> obtenerImagenesSecundarias(@Param("prendaId") UUID prendaId);
}