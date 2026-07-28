package com.example.ReVueltaBack.validaciones.transportista;

import com.example.ReVueltaBack.modelos.Transportista;

public interface IValidacionTransportista {

    void validarNombreObligatorio(String nombre);
    void validarZonaCoberturaLongitud(String zonaCobertura);
    void validarPlacaFormato(String placa);
    void validarTransportista(Transportista transportista);
}

