package com.example.ReVueltaBack.dtos.transaccion;

import java.time.LocalDate;
import java.util.UUID;

public record TransaccionPeticionDTO(

    UUID id,
    String tipo,
    Double monto,
    String referencia_pago,
    LocalDate fecha,
    String comprobante,
    String estado
    
) {}
