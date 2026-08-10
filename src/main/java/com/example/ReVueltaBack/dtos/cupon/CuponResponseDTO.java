package com.example.ReVueltaBack.dtos.cupon;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.Cupon;

public record CuponResponseDTO(
    UUID id,
    String codigo,
    String tipo,
    String valor,
    Integer usosMaximos,
    Integer usosActuales,
    LocalDateTime fechaExpiracion,
    UUID campanaId,
    String nombreCampana
) {

    public static CuponResponseDTO fromEntity(Cupon cupon) {
        return new CuponResponseDTO(
            cupon.getId(),
            cupon.getCodigo(),
            cupon.getTipo(),
            cupon.getValor(),
            cupon.getUsos_maximos(),
            cupon.getUsos_actuales(),
            cupon.getFecha_expiracion(),
            cupon.getCampana() != null ? cupon.getCampana().getId() : null,
            cupon.getCampana() != null ? cupon.getCampana().getNombre_campana() : null
        );
    }
}
