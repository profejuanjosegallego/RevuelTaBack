package com.example.ReVueltaBack.repositorios;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ReVueltaBack.modelos.Prenda;
import com.example.ReVueltaBack.modelos.Reporte;
import com.example.ReVueltaBack.modelos.Usuario;

@Repository
public interface IReporteRepository extends JpaRepository<Reporte, UUID> {

    //Consultas en el repositorio personalidas con JPA(Solo letura)

    List<Reporte> findByUsuario(Usuario usuario);
    List<Reporte> findByPrenda(Prenda prenda);
    List<Reporte> findByEstado(String estado);
    List<Reporte> findByResuelto(Boolean resuelto);
    List<Reporte> findAllByOrderByFechaDesc();

    //Consultas personalizadas con JPQL(Todas Habilitadas)

    @Query("SELECT r FROM Reporte r WHERE r.usuario.id = :usuarioId")
    List<Reporte> buscarPorUsuarioId(@Param("usuarioId") UUID usuarioId);

}
