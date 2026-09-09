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
public interface IReporteRepositorio extends JpaRepository<Reporte, UUID> {

    //Consultas en el repositorio personalidas con JPA(Solo letura)

    List<Reporte> findByUsuario(Usuario usuario);
    List<Reporte> findByPrenda(Prenda prenda);
    List<Reporte> findByEstado(String estado);
    List<Reporte> findByResuelto(Boolean resuelto);
    List<Reporte> findAllByOrderByFechaDesc();

    //Consultas personalizadas con JPQL(Todas Habilitadas)

    @Query("SELECT r FROM Reporte r WHERE r.usuario.id = :usuarioId")
    List<Reporte> buscarPorUsuarioId(@Param("usuarioId") UUID usuarioId);

    @Query("SELECT r FROM Reporte r WHERE r.prenda.id = :prendaId")
    List<Reporte> buscarPorPrenda(@Param("prendaId") UUID prendaId);

    @Query("SELECT r FROM Reporte r WHERE r.estado = :estado")
    List<Reporte> buscarPorEstado(@Param("estado") String estado);

    @Query("SELECT r FROM Reporte r WHERE r.resuelto = :resuelto")
    List<Reporte> buscarPorResuelto(@Param("resuelto") Boolean resuelto);

    @Query("SELECT r FROM Reporte r ORDER BY r.fecha DESC")
    List<Reporte> orderPorFechaDesc();

    @Query("""
        SELECT r
        FROM Reporte r
        JOIN r.prenda p
        JOIN p.usuario u
        WHERE u.id = :idUsuario
        """)
    List<Reporte> buscarPorUsuarioReportado(@Param("idUsuario") UUID idUsuario);
}
