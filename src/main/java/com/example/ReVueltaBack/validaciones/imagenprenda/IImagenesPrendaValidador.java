package com.example.ReVueltaBack.validaciones.imagenprenda;

import com.example.ReVueltaBack.modelos.ImagenPrenda;

public interface IImagenesPrendaValidador {

    void validarUrl(ImagenPrenda imagenPrenda);

    void validarTamano(ImagenPrenda imagenPrenda);

    void validarFormato(ImagenPrenda imagenPrenda);

    void validar(ImagenPrenda imagenPrenda);

}