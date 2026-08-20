package com.example.ReVueltaBack.dtos.imagenprenda;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.ImagenPrenda;
import com.example.ReVueltaBack.modelos.Prenda;

public class ImagenPrendaRequestDTO {

    private String url;
    private Boolean esPrincipal;
    private Integer orden;
    private String formato;
    private Integer tamanoKb;
    private UUID prendaId;

    public ImagenPrendaRequestDTO() {
    }

    public ImagenPrendaRequestDTO(String url, Boolean esPrincipal, Integer orden,
                                     String formato, Integer tamanoKb, UUID prendaId) {
        this.url = url;
        this.esPrincipal = esPrincipal;
        this.orden = orden;
        this.formato = formato;
        this.tamanoKb = tamanoKb;
        this.prendaId = prendaId;
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

    public UUID getPrendaId() {
        return prendaId;
    }

    public void setPrendaId(UUID prendaId) {
        this.prendaId = prendaId;
    }

    public ImagenPrenda toEntity() {
        ImagenPrenda entidad = new ImagenPrenda();
        entidad.setUrl(this.url);
        entidad.setEs_Principal(this.esPrincipal);
        entidad.setOrden(this.orden);
        entidad.setFormato(this.formato);
        entidad.setTamano_KB(this.tamanoKb);

        if (this.prendaId != null) {
            Prenda prenda = new Prenda();
            prenda.setId(this.prendaId);
            entidad.setPrenda(prenda);
        }

        return entidad;
    }
}
