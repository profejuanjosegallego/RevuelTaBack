package com.example.ReVueltaBack.dtos.prenda;

import java.time.LocalDate;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.Categoria;
import com.example.ReVueltaBack.modelos.EstadoPrenda;
import com.example.ReVueltaBack.modelos.Prenda;
import com.example.ReVueltaBack.modelos.Usuario;

public record PrendaRequestDTO(
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
    public Prenda toEntity(){
        Prenda prenda = new Prenda();
        
        Categoria categoria= new Categoria();
        categoria.setId(idCategoria);
        EstadoPrenda estadoPrenda = new EstadoPrenda();
        estadoPrenda.setId(idEstado);
        Usuario usuario = new Usuario();
        usuario.setId(idVendedor);

        prenda.setTitulo(titulo);
        prenda.setDescripcion(descripcion);
        prenda.setTalla(talla);
        prenda.setPrecio(precio);
        prenda.setFecha_publicacion(fechaPublicacion);
        prenda.setDisponible(disponible);
        prenda.setCategoria(categoria);
        prenda.setEstado(estadoPrenda);
        prenda.setUsuario(usuario);
        return prenda;
    }

}
