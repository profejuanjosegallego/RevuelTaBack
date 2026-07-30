package com.example.ReVueltaBack.repositorios;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ReVueltaBack.modelos.Categoria;
import com.example.ReVueltaBack.modelos.EstadoPrenda;
import com.example.ReVueltaBack.modelos.Prenda;
import com.example.ReVueltaBack.modelos.Usuario;

import java.util.List;

@Repository
public interface IPrendaRepositorio extends JpaRepository<Prenda, UUID> {
    //Consultas personalizadas con JPA (SOLO LECTURA)
    List<Prenda> findByCategoria(Categoria categoria);
    List<Prenda> findByEstado(EstadoPrenda estado);

    //Consulta personalizada con JPQL (SOLO LECTURA)
    @Query("SELECT p FROM Prenda p WHERE p.usuario = :vendedor")
    List<Prenda> findByVendedor(@Param("vendedor") Usuario vendedor);

    @Query("SELECT p FROM Prenda p WHERE LOWER(p.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
    List<Prenda> findByTituloContainingIgnoreCase(@Param("titulo") String titulo);

    @Query("SELECT p FROM Prenda p WHERE p.disponible = true")
    List<Prenda> findByDisponibleTrue();
}
