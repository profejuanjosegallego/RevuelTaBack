package com.example.ReVueltaBack.modelos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(name = "usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "prenda", nullable = false)
    private String prenda;

}
