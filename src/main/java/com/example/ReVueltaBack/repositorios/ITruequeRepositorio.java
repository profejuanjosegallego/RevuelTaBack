package com.example.ReVueltaBack.repositorios;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ReVueltaBack.modelos.Prenda;
import com.example.ReVueltaBack.modelos.Trueque;
import com.example.ReVueltaBack.modelos.Usuario;

@Repository
public interface ITruequeRepositorio extends JpaRepository<Trueque, UUID> {

    // CONSULTAS PERSONALIZADAS CON JPA (Solo lectura)
    List<Trueque> findByEstado(String estado);

    List<Trueque> findByProponente(Usuario proponente);

    List<Trueque> findByPrenda(Prenda prenda);

    List<Trueque> findByPrendaDeseada(Prenda prendaDeseada);

    List<Trueque> findByAceptado(Boolean aceptado);

    List<Trueque> findAllByOrderByFecha_propuestaDesc();

    List<Trueque> findByFecha_propuestaBetween(LocalDate desde, LocalDate hasta);

    // Consultas personalizadas con JPQL
    @Query("SELECT t FROM Trueque t " +
           "WHERE LOWER(t.estado) = LOWER(:estado)")
    List<Trueque> buscarPorEstado(String estado);

    @Query("SELECT t FROM Trueque t " +
           "WHERE t.proponente.id = :idProponente")
    List<Trueque> buscarPorProponenteId(UUID idProponente);

}