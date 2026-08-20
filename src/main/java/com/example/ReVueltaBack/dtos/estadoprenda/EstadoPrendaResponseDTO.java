package com.example.ReVueltaBack.dtos.estadoprenda;

import java.util.UUID;

import com.example.ReVueltaBack.modelos.EstadoPrenda;

public record EstadoPrendaResponseDTO(
    UUID id,
    String nombre,
    String descripcion,
    Integer nivelDesgaste,
    String colorEtiqueta,
    Boolean requiereRevision,
    Boolean activo
) {

    public static EstadoPrendaResponseDTO fromEntity(EstadoPrenda estadoPrenda) {
        return new EstadoPrendaResponseDTO(
            estadoPrenda.getId(),
            estadoPrenda.getNombre(),
            estadoPrenda.getDescripcion(),
            estadoPrenda.getNivelDesgaste(),
            estadoPrenda.getColorEtiqueta(),
            estadoPrenda.getRequiereRevision(),
            estadoPrenda.getActivo()
        );
    }
}
