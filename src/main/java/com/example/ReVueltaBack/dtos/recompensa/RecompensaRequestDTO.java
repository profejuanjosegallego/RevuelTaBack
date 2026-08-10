package com.example.ReVueltaBack.dtos.recompensa;

import com.example.ReVueltaBack.modelos.Recompensa;

public record RecompensaRequestDTO(
    String nombre,
    Integer puntosRequeridos,
    String descripcion,
    Integer existencias,
    String tipo,
    Boolean activa

) {

    public Recompensa toEntity(){
        Recompensa recompensa= new Recompensa();
        recompensa.setNombre(nombre);
        recompensa.setPuntos_requeridos(puntosRequeridos);
        recompensa.setDescripcion(descripcion);
        recompensa.setStock(existencias);
        recompensa.setTipo(tipo);
        recompensa.setActiva(true);
        return recompensa;
        
    }
}

