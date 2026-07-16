package com.example.ReVueltaBack.modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "prendas")
public class Prenda {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID )
    private UUID id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "talla")
    private String talla;

    @Column(name = "precio")
    private double precio;

    @Column(name = "fecha_publicacion")
    private LocalDate fecha_publicacion;

    @Column(name = "disponible")
    private boolean disponible;

    //Relacione tabla ususario (vendedor). Seed: id_vendedor -> usuarios.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_id_usuario")
    @JsonBackReference("usuario_prenda")
    private Usuario usuario;

    //Relacion tabla categorias. Seed: id_categoria -> categorias.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    //Relacion tabla estados_prenda. Seed: id_estado -> estados_prenda.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_estado")
    private EstadoPrenda estado;

    //Relacion tabla DetallePedido
    @OneToMany(mappedBy = "prenda")
    @JsonManagedReference("prenda_detallePedido")
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    public List<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }
    public void setDetallePedidos(List<DetallePedido> detallePedidos) {
        this.detallePedidos = detallePedidos;
    }
    // Relación tabla Reporte
    @OneToMany(mappedBy = "prenda")
    @JsonManagedReference("prenda_reporte")
    private List<Reporte> reportes = new ArrayList<>();

    public List<Reporte> getReportes() {
        return reportes;
    }
    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }
    // Relación tabla Trueque
    @OneToMany(mappedBy = "prenda")
    @JsonManagedReference("prenda_trueque")
    private List<Trueque> trueques = new ArrayList<>();

    public List<Trueque> getTrueques() {
        return trueques;
    }
    public void setTrueques(List<Trueque> trueques) {
        this.trueques = trueques;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public EstadoPrenda getEstado() {
        return estado;
    }
    public void setEstado(EstadoPrenda estado) {
        this.estado = estado;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getTalla() {
        return talla;
    }
    public void setTalla(String talla) {
        this.talla = talla;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public LocalDate getFecha_publicacion() {
        return fecha_publicacion;
    }
    public void setFecha_publicacion(LocalDate fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }
    public boolean isDisponible() {
        return disponible;
    }
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    



}
