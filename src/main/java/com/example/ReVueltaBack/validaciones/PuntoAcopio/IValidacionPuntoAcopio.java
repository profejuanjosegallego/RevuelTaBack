package com.example.ReVueltaBack.validaciones.puntoacopio;
import com.example.ReVueltaBack.modelos.PuntoAcopio;
public interface IValidacionPuntoAcopio {

    void validarNombreObligatorio(String nombre);

    void validarDireccionLongitud(String direccion);

    void validarCapacidadNoNegativo(Integer capacidad);

    void validar(PuntoAcopio puntoAcopio);

}