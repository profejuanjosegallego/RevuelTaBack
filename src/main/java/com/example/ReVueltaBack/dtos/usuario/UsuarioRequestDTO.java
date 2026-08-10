package com.example.ReVueltaBack.dtos.usuario;

import com.example.ReVueltaBack.modelos.Usuario;

public record UsuarioRequestDTO(
    String nombre,
    String correo,
    String contrasenaHash,
    String rol,
    Boolean activo,
    String colorAvatar
) {

    public Usuario toEntity() {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setContrasena_hash(contrasenaHash);
        usuario.setRol(rol);
        usuario.setActivo(activo);
        usuario.setColor_avatar(colorAvatar);
        return usuario;
    }
}
