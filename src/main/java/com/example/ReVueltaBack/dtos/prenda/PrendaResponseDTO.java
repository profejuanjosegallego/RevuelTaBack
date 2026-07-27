package com.example.ReVueltaBack.dtos.prenda;

import java.time.LocalDate;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.Categoria;
import com.example.ReVueltaBack.modelos.EstadoPrenda;
import com.example.ReVueltaBack.modelos.Prenda;
import com.example.ReVueltaBack.modelos.Usuario;

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
    public PrendaResponseDTO fromEntity(Prenda prenda, EstadoPrenda estadoPrenda, Categoria categoria, Usuario usuario ){
        return new PrendaResponseDTO(
            prenda.getId(),
            prenda.getTitulo(),
            prenda.getDescripcion(),
            prenda.getTalla(),
            prenda.getPrecio(),
            prenda.getFecha_publicacion(),
            prenda.isDisponible(),
            estadoPrenda.getId(),
            categoria.getId(),
            usuario.getId() 
        );
    }
}
