package com.example.ReVueltaBack.validaciones.categoria;

import com.example.ReVueltaBack.modelos.Categoria;

public interface IValidacionCategoria {

    void validarNombreObligatorio(String nombre);

    void validarDescripcionLongitud(String descripcion);

    void validarSlugFormato(String slug);

    void validarCategoria(Categoria categoria);

}
