package com.example.ReVueltaBack.modelos;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "trueques")
public class Trueque {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "fecha_propuesta", nullable = false)
    private LocalDate fecha_propuesta;

    @Column(name = "fecha_respuesta")
    private LocalDate fecha_respuesta;

    @Column(name = "mensaje", length = 500)
    private String mensaje;

    @Column(name = "valor_estimado")
    private Double valor_estimado;

    @Column(name = "aceptado")
    private Boolean aceptado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_id_prenda_ofrecida")
    @JsonBackReference("prenda-ofrecida")
    private Prenda id_prenda_ofrecida;

   

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
    public LocalDate getFecha_propuesta() {
        return fecha_propuesta;
    }
    public void setFecha_propuesta(LocalDate fecha_propuesta) {
        this.fecha_propuesta = fecha_propuesta;
    }
    public LocalDate getFecha_respuesta() {
        return fecha_respuesta;
    }
    public void setFecha_respuesta(LocalDate fecha_respuesta) {
        this.fecha_respuesta = fecha_respuesta;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public Double getValor_estimado() {
        return valor_estimado;
    }
    public void setValor_estimado(Double valor_estimado) {
        this.valor_estimado = valor_estimado;
    }
    public Boolean getAceptado() {
        return aceptado;
    }
    public void setAceptado(Boolean aceptado) {
        this.aceptado = aceptado;
    }
    public Prenda getId_prenda_ofrecida() {
        return id_prenda_ofrecida;
    }
    public void setId_prenda_ofrecida(Prenda id_prenda_ofrecida) {
        this.id_prenda_ofrecida = id_prenda_ofrecida;
    }
    public Prenda getId_prenda_deseada() {
        return id_prenda_deseada;
    }
    public void setId_prenda_deseada(Prenda  id_prenda_deseada) {
        this.id_prenda_deseada = id_prenda_deseada;
    }
    public Usuario getId_proponente() {
        return id_proponente;
    }
    public void setId_proponente(Usuario id_proponente) {
        this.id_proponente = id_proponente;
    }

}