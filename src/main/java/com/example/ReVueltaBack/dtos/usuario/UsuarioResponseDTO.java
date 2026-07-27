package com.example.ReVueltaBack.dtos.Usuario;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioResponseDTO(

    
    UUID id,
    String nombre,
    String correo,
    String rol,
    Boolean activo,
    String colorAvatar,
    LocalDateTime fechaRegistro
) {}
