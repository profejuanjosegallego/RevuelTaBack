package com.example.ReVueltaBack.dtos.recompensa;

import java.util.UUID;
import com.example.ReVueltaBack.modelos.Recompensa;

public record RecompensaResponseDTO(
    UUID id,
    String nombre,
    Integer puntosRequeridos,
    String descripcion,
    Integer existencias,
    String tipo,
    Boolean activa
) {

    public static RecompensaResponseDTO fromEntity(Recompensa recompensa) {
        return new RecompensaResponseDTO(
            recompensa.getId(),
            recompensa.getNombre(),
            recompensa.getPuntos_requeridos(),
            recompensa.getDescripcion(),
            recompensa.getStock(),
            recompensa.getTipo(),
            recompensa.getActiva()
        );
    }
}