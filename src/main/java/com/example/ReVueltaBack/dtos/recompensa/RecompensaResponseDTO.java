package com.example.ReVueltaBack.dtos.recompensa;



import com.example.ReVueltaBack.modelos.Recompensa;

public record RecompensaResponseDTO(

    
    String nombre,
    Integer puntosRequeridos,
    String descripcion, 
    Integer existencias,
    String tipo,
    Boolean activa

) {

    public RecompensaResponseDTO fromEntity(Recompensa recompensa) {
    
    
        return new RecompensaResponseDTO(
    
        recompensa.getNombre(), 
        recompensa.getPuntos_requeridos(), 
        recompensa.getDescripcion(),
        recompensa.getStock(),
        recompensa.getTipo(),
        recompensa.getActiva()
    
        );
    }

}
