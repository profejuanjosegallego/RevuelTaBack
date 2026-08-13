package com.example.ReVueltaBack.dtos.prenda;

import java.time.LocalDate;
import java.util.UUID;


import com.example.ReVueltaBack.modelos.Prenda;


public record PrendaResponseDTO(
    UUID id,
    String titulo,
    String descripcion,
    String talla,
    Double precio,
    LocalDate fechaPublicacion,
    Boolean disponible,
    UUID idCategoria,
    UUID idEstado,
    UUID idVendedor
    
) {
    public static PrendaResponseDTO fromEntity(Prenda prenda){
        return new PrendaResponseDTO(
            prenda.getId(),
            prenda.getTitulo(),
            prenda.getDescripcion(),
            prenda.getTalla(),
            prenda.getPrecio(),
            prenda.getFecha_publicacion(),
            prenda.isDisponible(),
            prenda.getCategoria().getId(),  
            prenda.getEstado().getId(),     
            prenda.getUsuario().getId()
        );
    }
}
