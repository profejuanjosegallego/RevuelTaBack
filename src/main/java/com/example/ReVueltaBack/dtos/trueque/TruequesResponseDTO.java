package com.example.ReVueltaBack.dtos.trueque;

import com.example.ReVueltaBack.modelos.Trueque;

import java.time.LocalDate;
import java.util.UUID;


public class TruequesResponseDTO {

    private UUID id;
    private String estado;
    private LocalDate fechaPropuesta;
    private LocalDate fechaRespuesta;
    private String mensaje;
    private Double valorEstimado;
    private Boolean aceptado;
    private UUID idPrendaOfrecida;
    private UUID idPrendaDeseada;
    private UUID idProponente;

    public TruequesResponseDTO() {
    }

    public TruequesResponseDTO(UUID id, String estado, LocalDate fechaPropuesta, LocalDate fechaRespuesta,
                                String mensaje, Double valorEstimado, Boolean aceptado,
                                UUID idPrendaOfrecida, UUID idPrendaDeseada, UUID idProponente) {
        this.id = id;
        this.estado = estado;
        this.fechaPropuesta = fechaPropuesta;
        this.fechaRespuesta = fechaRespuesta;
        this.mensaje = mensaje;
        this.valorEstimado = valorEstimado;
        this.aceptado = aceptado;
        this.idPrendaOfrecida = idPrendaOfrecida;
        this.idPrendaDeseada = idPrendaDeseada;
        this.idProponente = idProponente;
    }

    // ---- Getters y setters ----

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaPropuesta() {
        return fechaPropuesta;
    }

    public void setFechaPropuesta(LocalDate fechaPropuesta) {
        this.fechaPropuesta = fechaPropuesta;
    }

    public LocalDate getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(LocalDate fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Double getValorEstimado() {
        return valorEstimado;
    }

    public void setValorEstimado(Double valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    public Boolean getAceptado() {
        return aceptado;
    }

    public void setAceptado(Boolean aceptado) {
        this.aceptado = aceptado;
    }

    public UUID getIdPrendaOfrecida() {
        return idPrendaOfrecida;
    }

    public void setIdPrendaOfrecida(UUID idPrendaOfrecida) {
        this.idPrendaOfrecida = idPrendaOfrecida;
    }

    public UUID getIdPrendaDeseada() {
        return idPrendaDeseada;
    }

    public void setIdPrendaDeseada(UUID idPrendaDeseada) {
        this.idPrendaDeseada = idPrendaDeseada;
    }

    public UUID getIdProponente() {
        return idProponente;
    }

    public void setIdProponente(UUID idProponente) {
        this.idProponente = idProponente;
    }

    /**
     * Construye un DTO de salida a partir de la entidad JPA.
     * Extrae únicamente el id de cada relación (Prenda / Usuario),
     * nunca el objeto completo, evitando así filtrar la entidad JPA.
     */
    public static TruequesResponseDTO fromEntity(Trueque trueque) {
        if (trueque == null) {
            return null;
        }

        TruequesResponseDTO dto = new TruequesResponseDTO();
        dto.setId(trueque.getId());
        dto.setEstado(trueque.getEstado());
        dto.setFechaPropuesta(trueque.getFecha_propuesta());
        dto.setFechaRespuesta(trueque.getFecha_respuesta());
        dto.setMensaje(trueque.getMensaje());
        dto.setValorEstimado(trueque.getValor_estimado());
        dto.setAceptado(trueque.getAceptado());

        if (trueque.getPrenda() != null) {
            dto.setIdPrendaOfrecida(trueque.getPrenda().getId());
        }

        if (trueque.getPrendaDeseada() != null) {
            dto.setIdPrendaDeseada(trueque.getPrendaDeseada().getId());
        }

        if (trueque.getProponente() != null) {
            dto.setIdProponente(trueque.getProponente().getId());
        }

        return dto;
    }
}