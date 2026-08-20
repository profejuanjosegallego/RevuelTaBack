package com.example.ReVueltaBack.validaciones.usuario;

import com.example.ReVueltaBack.modelos.Usuario;

public interface IValidacionUsuario {

    //obligatoriedad en el nombre de usuario
    void validarNombreObligatorio(String nombre);

    //Formato del correo
    void validarCorreoFormato(String correo);

    //tamaño del hash contraseña
    void validarContrasenaLongitud(String contrasenaHash);

    //funcion para unificar las validaciones
    void validarUsuario(Usuario usuario);

}
