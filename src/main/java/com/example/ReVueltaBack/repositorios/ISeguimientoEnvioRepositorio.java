package com.example.ReVueltaBack.repositorios;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ReVueltaBack.modelos.Envio;
import com.example.ReVueltaBack.modelos.SeguimientoEnvio;

@Repository
public interface ISeguimientoEnvioRepositorio extends JpaRepository<SeguimientoEnvio, UUID>{

    List<SeguimientoEnvio> findByEnvio(Envio envio);
    List<SeguimientoEnvio> findByEstado(String estado);
    List<SeguimientoEnvio> findAllByOrderByFechaHoraDesc();
    
    @Query("SELECT s FROM SeguimientoEnvio s WHERE s.envio.id = :envioId")
    List<SeguimientoEnvio> buscarPorEnviosId(@Param("envioId") UUID envioId);

    @Query("SELECT s FROM SeguimientoEnvio s WHERE s.envio.id = :envioId AND s.fechaHora = (SELECT MAX(s2.fechaHora) FROM SeguimientoEnvio s2 WHERE s2.envio.id = :envioId)")
    List<SeguimientoEnvio> buscarUltimoSeguimientoPorEnvioId(@Param("envioId") UUID envioId);
}
