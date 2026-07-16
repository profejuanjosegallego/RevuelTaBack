package com.example.ReVueltaBack.modelos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// HU USR-01 — Modelo de la entidad Usuario (modulo comun de Autenticacion).
// Muchas tablas apuntan a usuarios con una FK, asi que aqui las relaciones son INVERSAS.
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 120)
    private String nombre;

    @Column(nullable = false, unique = true, length = 160)
    private String correo;

    // Se guarda SIEMPRE la contrasena cifrada (hash BCrypt), nunca en texto plano.
    @Column(name = "contrasena_hash", nullable = false)
    private String contrasena_hash;

    @Column(nullable = false, length = 20)
    private String rol;               // "docente" | "estudiante"

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(name = "color_avatar", length = 9)
    private String color_avatar;

    @Column(name = "fecha_registro")
    private LocalDateTime fecha_registro;

    // ===== Relaciones INVERSAS (@OneToMany) =====
    // La entidad DUEÑA de cada relacion es la que tiene el @ManyToOne + @JoinColumn.
    // Aqui solo mapeamos la vuelta: el mappedBy apunta al NOMBRE DEL ATRIBUTO Java
    // del dueño (no al nombre de la columna), por eso debe calzar EXACTO.

    // Prenda.usuario (columna fk_id_usuario) -> prendas publicadas por el usuario.
    @OneToMany(mappedBy = "usuario")
    private List<Prenda> prendas = new ArrayList<>();

    // Pedido.usuario (columna usuario_id) -> pedidos realizados por el usuario.
    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos = new ArrayList<>();

    // Reseña.autor (columna id_autor) -> reseñas escritas por el usuario.
    @OneToMany(mappedBy = "autor")
    private List<Reseña> reseñas = new ArrayList<>();

    // Reporte.usuario (columna id_usuario) -> reportes hechos por el usuario.
    @OneToMany(mappedBy = "usuario")
    private List<Reporte> reportes = new ArrayList<>();

    // Trueque.proponente (columna id_proponente) -> trueques propuestos por el usuario.
    @OneToMany(mappedBy = "proponente")
    private List<Trueque> trueques = new ArrayList<>();

    public Usuario() {
    }

    // ===== Getters y setters (estilo snake_case, como el resto del proyecto) =====

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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena_hash() {
        return contrasena_hash;
    }

    public void setContrasena_hash(String contrasena_hash) {
        this.contrasena_hash = contrasena_hash;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getColor_avatar() {
        return color_avatar;
    }

    public void setColor_avatar(String color_avatar) {
        this.color_avatar = color_avatar;
    }

    public LocalDateTime getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDateTime fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public List<Prenda> getPrendas() {
        return prendas;
    }

    public void setPrendas(List<Prenda> prendas) {
        this.prendas = prendas;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Reseña> getReseñas() {
        return reseñas;
    }

    public void setReseñas(List<Reseña> reseñas) {
        this.reseñas = reseñas;
    }

    public List<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }

    public List<Trueque> getTrueques() {
        return trueques;
    }

    public void setTrueques(List<Trueque> trueques) {
        this.trueques = trueques;
    }
}
