package com.example.ReVueltaBack.validaciones.Transportista;

import com.example.ReVueltaBack.modelos.Transportista;

public interface ITransportistasValidador {

    void validarNombreObligatorio(String nombre);
    void validarZonaCoberturaLongitud(String zonaCobertura);
    void validarPlacaFormato(String placa);
    void validar(Transportista transportista);

}