package com.example.ReVueltaBack.repositorios;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ReVueltaBack.modelos.Transaccion;

@Repository
public interface ITransaccionRepositorio extends JpaRepository<Transaccion,UUID> {

    // CONSULTAS PERSONALIZADAS CON JPA (Solo lectura)
    List <Transaccion> findByTipo(String tipo);

    // Consultas personalizadas con JPQL (Todas HABILITADAS)
    @Query("SELECT t FROM Transaccion t " +
       "WHERE LOWER(u.tipo) = LOWER(:tipo)")
    List<Transaccion> buscarPorTipo (String tipo);


}
