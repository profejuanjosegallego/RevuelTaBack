package com.example.ReVueltaBack.repositorios;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ReVueltaBack.modelos.Categoria;
import java.util.List;
import java.util.Optional;


@Repository
public interface ICategoriasRepositorio extends JpaRepository<Categoria,UUID> {

    //consultas personalizadas con jpa (solo lectura)
    List<Categoria> findByNombreContainigIgnoreCase(String nombre);

    List<Categoria> findByDescripcion(String descripcion);

    Optional<Categoria> findBySlug(String slug);

    Optional<Categoria> findByIcono(String icono);

    List<Categoria> findByActivaTrue();

    Optional<Categoria> findByOrden(Integer orden);

    List<Categoria> findAll();



    //consultas personalizadas con jpql (todas habilitadas)
    @Query("SELECT c FROM Categoria c" +
        "WHERE LOWER(c.nombre) LIKE LOWER(:nombre) = :nombreCategoria")
    List<Categoria> buscarPorNombre(@Param("nombreCategoria")String nombre);
    
}
