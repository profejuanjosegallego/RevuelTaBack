package com.example.ReVueltaBack.dtos.Usuario;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.Usuario;

import io.swagger.v3.oas.annotations.media.Schema;

// HU USR-D01 — DTO de SALIDA de Usuario.
// Nunca expone el hash de la contrasena ni las colecciones de la entidad.
@Schema(name = "UsuarioResponse", description = "Datos publicos de un usuario")
public record UsuarioResponseDTO(

    @Schema(description = "Identificador unico", example = "3f2504e0-4f89-11d3-9a0c-0305e82c3301")
    UUID id,

    @Schema(description = "Nombre completo", example = "Juan José Gallego Mesa")
    String nombre,

    @Schema(description = "Correo unico", example = "juan.gallego@cesde.edu.co")
    String correo,

    @Schema(description = "Rol dentro de la plataforma", example = "docente")
    String rol,

    @Schema(description = "Indica si la cuenta esta habilitada", example = "true")
    Boolean activo,

    @Schema(description = "Color del avatar en hexadecimal", example = "#1D4ED8")
    String colorAvatar,

    @Schema(description = "Momento en que se registro la cuenta", example = "2026-08-17T09:30:00")
    LocalDateTime fechaRegistro
) {

    public static UsuarioResponseDTO fromEntity(Usuario usuario) {

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
