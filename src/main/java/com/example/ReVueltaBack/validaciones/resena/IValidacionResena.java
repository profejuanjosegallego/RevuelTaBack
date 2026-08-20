package com.example.ReVueltaBack.validaciones.resena;

import java.time.LocalDate;

import com.example.ReVueltaBack.modelos.Resena;

public interface IValidacionResena {

    void validarTituloObligatorio(String titulo);
    void validarComentarioLongitud(String comentario);
    void validarFechaNoFutura(LocalDate fecha);
    void validar(Resena resena);

}
