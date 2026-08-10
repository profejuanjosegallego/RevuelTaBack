package com.example.ReVueltaBack.dtos.cupon;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.Campana;
import com.example.ReVueltaBack.modelos.Cupon;

public record CuponRequestDTO(
    String codigo,
    String tipo,
    String valor,
    Integer usosMaximos,
    Integer usosActuales,
    LocalDateTime fechaExpiracion,
    UUID campanaId
) {

    // El cupón siempre pertenece a una campaña (relación @ManyToOne obligatoria),
    // por eso la campaña ya resuelta (buscada por campanaId en el servicio) se recibe aquí.
    public Cupon toEntity(Campana campana) {
        Cupon cupon = new Cupon();
        cupon.setCodigo(codigo);
        cupon.setTipo(tipo);
        cupon.setValor(valor);
        cupon.setUsos_maximos(usosMaximos);
        cupon.setUsos_actuales(usosActuales != null ? usosActuales : 0);
        cupon.setFecha_expiracion(fechaExpiracion);
        cupon.setCampana(campana);
        return cupon;
    }
}
