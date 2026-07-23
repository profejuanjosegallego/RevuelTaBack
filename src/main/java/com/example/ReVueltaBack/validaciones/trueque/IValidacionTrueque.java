package com.example.ReVueltaBack.validaciones.trueque;

import java.time.LocalDate;

import com.example.ReVueltaBack.modelos.Prenda;
import com.example.ReVueltaBack.modelos.Trueque;

public interface IValidacionTrueque {

    // Estado debe existir y ser uno de los valores permitidos
    void validarEstadoValido(String estado);

    // La fecha de propuesta es obligatoria
    void validarFechaPropuestaObligatoria(LocalDate fechaPropuesta);

    // Si existe fecha de respuesta, no puede ser anterior a la fecha de propuesta
    void validarFechaRespuestaCoherente(LocalDate fechaPropuesta, LocalDate fechaRespuesta);

    // El mensaje no puede superar la longitud permitida
    void validarMensajeLongitud(String mensaje);

    // Si existe valor estimado, no puede ser negativo
    void validarValorEstimadoPositivo(Double valorEstimado);

    // La prenda ofrecida es obligatoria
    void validarPrendaOfrecidaObligatoria(Prenda prenda);

    // La prenda deseada es obligatoria
    void validarPrendaDeseadaObligatoria(Prenda prendaDeseada);

    // La prenda ofrecida y la deseada no pueden ser la misma
    void validarPrendasDiferentes(Prenda prenda, Prenda prendaDeseada);

    // El proponente es obligatorio
    void validarProponenteObligatorio(Object proponente);

    // Funcion para unificar las validaciones
    void validarTrueque(Trueque trueque);

}