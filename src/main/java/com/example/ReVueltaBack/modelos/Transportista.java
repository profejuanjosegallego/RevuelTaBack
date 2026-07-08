package com.example.ReVueltaBack.modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "transportistas")
public class Transportista {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "nombre", nullable = false, unique = false, length = 50)
    private String nombre;
    @Column(name = "tipo_vehiculo", nullable = false, unique = false, length = 50)
    private String tipo_vehiculo;
    @Column(name = "placa", nullable = false, unique = false, length = 50)
    private String placa;
    @Column(name = "telefono", nullable = false, unique = false, length = 50)
    private String telefono;
    @Column(name = "zona_cobertura", nullable = false, unique = false, length = 50)
    private String zona_cobertura;
    @Column(name = "disponoble", nullable = false, unique = false)
    private Boolean disponible;

    @OneToMany (mappedBy = "transportista")
    @JsonManagedReference
    private List <Envio> envios = new ArrayList<>(); 
    
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTipo_vehiculo() {
        return tipo_vehiculo;
    }
    public void setTipo_vehiculo(String tipo_vehiculo) {
        this.tipo_vehiculo = tipo_vehiculo;
    }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getZona_cobertura() {
        return zona_cobertura;
    }
    public void setZona_cobertura(String zona_cobertura) {
        this.zona_cobertura = zona_cobertura;
    }
    public Boolean getDisponible() {
        return disponible;
    }
    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }



}
