package com.example.ReVueltaBack.dtos.trueque;

import com.example.ReVueltaBack.modelos.Trueque;
import com.example.ReVueltaBack.modelos.Prenda;
import com.example.ReVueltaBack.modelos.Usuario;

import java.time.LocalDate;
import java.util.UUID;


public class TruequesRequestDTO {

    private String estado;
    private LocalDate fechaPropuesta;
    private LocalDate fechaRespuesta;
    private String mensaje;
    private Double valorEstimado;
    private Boolean aceptado;
    private UUID idPrendaOfrecida;
    private UUID idPrendaDeseada;
    private UUID idProponente;

    public TruequesRequestDTO() {
    }

    public TruequesRequestDTO(String estado, LocalDate fechaPropuesta, LocalDate fechaRespuesta,
                               String mensaje, Double valorEstimado, Boolean aceptado,
                               UUID idPrendaOfrecida, UUID idPrendaDeseada, UUID idProponente) {
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
     * Convierte este DTO a la entidad JPA Trueque.
     *
     * NOTA: aquí solo se instancian referencias "livianas" (Prenda/Usuario
     * con únicamente el id seteado) para poder asociarlas a la entidad.
     * Si tu Service necesita las entidades completas y gestionadas por JPA,
     * busca las reales con tu repositorio (IPrendaRepositorio.findById(...),
     * UsuarioRepository.findById(...)) y sobreescribe esas referencias
     * después de llamar a este método.
     */
    public Trueque toEntity() {
        Trueque trueque = new Trueque();
        trueque.setEstado(this.estado);
        trueque.setFecha_propuesta(this.fechaPropuesta);
        trueque.setFecha_respuesta(this.fechaRespuesta);
        trueque.setMensaje(this.mensaje);
        trueque.setValor_estimado(this.valorEstimado);
        trueque.setAceptado(this.aceptado);

        if (this.idPrendaOfrecida != null) {
            Prenda prendaOfrecida = new Prenda();
            prendaOfrecida.setId(this.idPrendaOfrecida);
            trueque.setPrenda(prendaOfrecida);
        }

        if (this.idPrendaDeseada != null) {
            Prenda prendaDeseada = new Prenda();
            prendaDeseada.setId(this.idPrendaDeseada);
            trueque.setPrendaDeseada(prendaDeseada);
        }

        if (this.idProponente != null) {
            Usuario proponente = new Usuario();
            proponente.setId(this.idProponente);
            trueque.setProponente(proponente);
        }

        return trueque;
    }
}