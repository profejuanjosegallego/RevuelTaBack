package com.example.ReVueltaBack.validaciones.prenda;

import com.example.ReVueltaBack.modelos.Prenda;

public interface IValidacionPrenda {
    void validarTituloObligatorio(String titulo);

    void validarDescripcionLongitud(String descripcion);

    void validarPrecioPositivo(Double precio);

    void validar(Prenda prendas);
}
