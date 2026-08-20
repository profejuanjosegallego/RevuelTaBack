package com.example.ReVueltaBack.dtos.usuario;

import com.example.ReVueltaBack.modelos.Usuario;

import io.swagger.v3.oas.annotations.media.Schema;

// HU USR-D01 — DTO de ENTRADA de Usuario.
// Regla de oro: el cliente NUNCA envia el hash, envia la contrasena en texto
// plano y el SERVICIO es quien la cifra (BCrypt) antes de guardarla. Por eso
// toEntity() recibe el hash ya calculado.
@Schema(name = "UsuarioRequest", description = "Datos para registrar o actualizar un usuario")
public record UsuarioRequestDTO(

    @Schema(description = "Nombre completo del usuario", example = "Juan José Gallego Mesa", maxLength = 120)
    String nombre,

    @Schema(description = "Correo unico del usuario, sirve para iniciar sesion", example = "juan.gallego@cesde.edu.co", maxLength = 160)
    String correo,

    @Schema(description = "Contrasena en texto plano. El servidor la cifra con BCrypt antes de guardarla", example = "MiClaveSegura123", minLength = 8)
    String contrasena,

    @Schema(description = "Rol del usuario dentro de la plataforma", example = "estudiante", allowableValues = { "docente", "estudiante" })
    String rol,

    @Schema(description = "Indica si la cuenta esta habilitada. Si se omite, se asume true", example = "true")
    Boolean activo,

    @Schema(description = "Color del avatar en formato hexadecimal", example = "#1D4ED8", maxLength = 9)
    String colorAvatar
) {

    /**
     * Convierte el DTO en la entidad JPA.
     *
     * @param contrasenaHash contrasena YA cifrada por el servicio (BCrypt, 60 caracteres).
     */
    public Usuario toEntity(String contrasenaHash) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setCorreo(correo != null ? correo.trim().toLowerCase() : null);
        usuario.setContrasena_hash(contrasenaHash);
        usuario.setRol(rol);
        usuario.setActivo(activo != null ? activo : Boolean.TRUE);
        usuario.setColor_avatar(colorAvatar);
        return usuario;
    }
}
