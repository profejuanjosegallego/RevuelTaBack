package com.example.ReVueltaBack.modelos;

import java.time.LocalDateTime;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reportes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "motivo", nullable = false)
    private String motivo;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "prioridad", nullable = false)
    private String prioridad;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "resuelto", nullable = false)
    private Boolean resuelto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prenda", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Prenda prenda;

}
