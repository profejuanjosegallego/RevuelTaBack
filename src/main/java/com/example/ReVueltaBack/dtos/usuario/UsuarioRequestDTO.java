package com.example.ReVueltaBack.dtos.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.ReVueltaBack.modelos.Usuario;

public record UsuarioRequestDTO( 
    
    String nombre,
    String correo,
    String contraseña,
    String rol

) {

    public Usuario toEntity(){

        Usuario usuario= new Usuario();
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setRol(rol);
        usuario.setActivo(true);
        usuario.setColor_avatar("#2563EB");
        usuario.setFecha_registro(LocalDateTime.now());
        return usuario;


    }
}
