package com.example.ReVueltaBack.validaciones.estadoPrenda;

import com.example.ReVueltaBack.modelos.EstadoPrenda;

public interface IValidacionEstadoPrenda {

    //obligatoriedad en el nombre del estado
    void validarNombreObligatorio(String nombre);

    //longitud de la descripcion
    void validarDescripcionLongitud(String descripcion);

    //rango del nivel de desgaste
    void validarNivelDesgasteRango(Integer nivelDesgaste);

    //funcion para unificar las validaciones
    void validar(EstadoPrenda estadoPrenda);

}
