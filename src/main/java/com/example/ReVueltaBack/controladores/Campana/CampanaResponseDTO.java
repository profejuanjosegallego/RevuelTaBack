package com.example.ReVueltaBack.dtos.Campana;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.Campana;

public record CampanaResponseDTO(
    UUID id,
    String nombreCampana,
    String descripcionCampana,
    LocalDateTime fechaInicio,
    LocalDateTime fechaFinal,
    Double descuentoPct,
    Boolean activa,
    Integer cantidadCupones
) {

    public static CampanaResponseDTO fromEntity(Campana campana) {
        return new CampanaResponseDTO(
            campana.getId(),
            campana.getNombre_campana(),
            campana.getDescripcion_campana(),
            campana.getFecha_inicio(),
            campana.getFecha_final(),
            campana.getDescuento_pct(),
            campana.getActiva(),
            campana.getCupones() != null ? campana.getCupones().size() : 0
        );
    }
}
