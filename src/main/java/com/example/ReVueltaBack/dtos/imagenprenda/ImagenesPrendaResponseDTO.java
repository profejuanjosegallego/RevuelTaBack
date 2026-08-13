package com.example.ReVueltaBack.dtos.imagenprenda;
import java.time.LocalDate;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.ImagenPrenda;

public class ImagenesPrendaResponseDTO {

    private UUID id;
    private String url;
    private Boolean esPrincipal;
    private Integer orden;
    private String formato;
    private Integer tamanoKb;
    private LocalDate fechaSubida;
    private UUID prendaId;

    public ImagenesPrendaResponseDTO() {
    }

    public ImagenesPrendaResponseDTO(UUID id, String url, Boolean esPrincipal, Integer orden,
                                      String formato, Integer tamanoKb, LocalDate fechaSubida,
                                      UUID prendaId) {
        this.id = id;
        this.url = url;
        this.esPrincipal = esPrincipal;
        this.orden = orden;
        this.formato = formato;
        this.tamanoKb = tamanoKb;
        this.fechaSubida = fechaSubida;
        this.prendaId = prendaId;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(Boolean esPrincipal) {
        this.esPrincipal = esPrincipal;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Integer getTamanoKb() {
        return tamanoKb;
    }

    public void setTamanoKb(Integer tamanoKb) {
        this.tamanoKb = tamanoKb;
    }

    public LocalDate getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(LocalDate fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    public UUID getPrendaId() {
        return prendaId;
    }

    public void setPrendaId(UUID prendaId) {
        this.prendaId = prendaId;
    }

    public static ImagenesPrendaResponseDTO fromEntity(ImagenPrenda entidad) {
        UUID idPrenda = (entidad.getPrenda() != null) ? entidad.getPrenda().getId() : null;

        return new ImagenesPrendaResponseDTO(
                entidad.getId(),
                entidad.getUrl(),
                entidad.getEs_Principal(),
                entidad.getOrden(),
                entidad.getFormato(),
                entidad.getTamaño_KB(),
                entidad.getFecha_subida(),
                idPrenda
        );
    }
}
    
