package com.example.ReVueltaBack.dtos.campana;

import java.time.LocalDateTime;

import com.example.ReVueltaBack.modelos.Campana;

public record CampanaRequestDTO(
    String nombreCampana,
    String descripcionCampana,
    LocalDateTime fechaInicio,
    LocalDateTime fechaFinal,
    Double descuentoPct,
    Boolean activa
) {

    public Campana toEntity() {
        Campana campana = new Campana();
        campana.setNombre_campana(nombreCampana);
        campana.setDescripcion_campana(descripcionCampana);
        campana.setFecha_inicio(fechaInicio);
        campana.setFecha_final(fechaFinal);
        campana.setDescuento_pct(descuentoPct);
        campana.setActiva(activa);
        return campana;
    }
}
