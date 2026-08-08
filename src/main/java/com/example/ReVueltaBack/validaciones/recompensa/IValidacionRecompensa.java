package com.example.ReVueltaBack.validaciones.recompensa;

import com.example.ReVueltaBack.modelos.Recompensa;

public interface IValidacionRecompensa {

    void validarNombreObligatorio (String nombre);

    void validarDescripcionLongitud (String descripcion);

    void validarTipoPermitido (String tipo);

    void validar (Recompensa recompensa);

}
