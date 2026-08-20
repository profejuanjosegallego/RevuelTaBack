package com.example.ReVueltaBack.dtos.estadoprenda;

import com.example.ReVueltaBack.modelos.EstadoPrenda;

public record EstadoPrendaRequestDTO(
    String nombre,
    String descripcion,
    Integer nivelDesgaste,
    String colorEtiqueta,
    Boolean requiereRevision,
    Boolean activo
) {

    public EstadoPrenda toEntity() {
        EstadoPrenda estadoPrenda = new EstadoPrenda();
        estadoPrenda.setNombre(nombre);
        estadoPrenda.setDescripcion(descripcion);
        estadoPrenda.setNivelDesgaste(nivelDesgaste);
        estadoPrenda.setColorEtiqueta(colorEtiqueta);
        estadoPrenda.setRequiereRevision(requiereRevision != null ? requiereRevision : false);
        estadoPrenda.setActivo(activo != null ? activo : true);
        return estadoPrenda;
    }
}
