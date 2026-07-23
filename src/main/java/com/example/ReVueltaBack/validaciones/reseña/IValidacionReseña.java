package com.example.ReVueltaBack.validaciones.reseña;

import java.time.LocalDate;

import com.example.ReVueltaBack.modelos.Reseña;

public interface IValidacionReseña {

    void validarTituloObligatorio(String titulo);
    void validarComentarioLongitud(String comentario);
    void validarFechaNoFutura(LocalDate fecha);
    void validar(Reseña reseña);

}
