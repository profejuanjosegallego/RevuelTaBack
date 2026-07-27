package com.example.ReVueltaBack.dtos.Usuario;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.Usuario;

public record UsuarioResponseDTO(
    
    UUID id,
    String nombre,
    String correo,
    String rol,
    Boolean activo,
    String colorAvatar,
    LocalDateTime fechaRegistro
) {

    public UsuarioResponseDTO fromEntity(Usuario usuario){


        return new UsuarioResponseDTO(
            usuario.getId(),
            usuario.getNombre(),
            usuario.getCorreo(),
            usuario.getRol(),
            usuario.getActivo(),
            usuario.getColor_avatar(),
            usuario.getFecha_registro()
        );

    }
}
