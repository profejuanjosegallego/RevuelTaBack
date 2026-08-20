package com.example.ReVueltaBack.dtos.categoria;

import java.util.UUID;

import com.example.ReVueltaBack.modelos.Categoria;

public record CategoriaResponseDTO(

        UUID id,
        String nombre,
        String descripcion,
        String slug,
        String icono,
        Boolean activa,
        Integer orden
){
    public static CategoriaResponseDTO fromEntity(Categoria Categoria) {

        return new CategoriaResponseDTO(
                Categoria.getId(),
                Categoria.getNombre(),
                Categoria.getDescripcion(),
                Categoria.getSlug(),
                Categoria.getIcono(),
                Categoria.getActiva(),
                Categoria.getOrden()

        );
    }
}
