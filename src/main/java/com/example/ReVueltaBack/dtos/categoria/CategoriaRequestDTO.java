package com.example.ReVueltaBack.dtos.categoria;

import com.example.ReVueltaBack.modelos.Categoria;

public record CategoriaRequestDTO(
    String nombre,
    String descripcion,
    String slug,
    String icono,
    Boolean activa,
    Integer orden
) {
    public Categoria toEntity(){
        Categoria categoria=new Categoria();
        categoria.setNombre(this.nombre);
        categoria.setDescripcion(this.descripcion);
        categoria.setSlug(this.slug);
        categoria.setIcono(this.icono);
        categoria.setActiva(this.activa);
        categoria.setOrden(this.orden);
        return categoria;
        
    }

}
