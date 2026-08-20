package com.example.ReVueltaBack.validaciones.usuario;

import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.modelos.Usuario;

@Component
public class ValidacionUsuarioImpl implements IValidacionUsuario {


    //Patron de correo valido
    private static final Pattern PATRON_CORREO=Pattern.compile("^[\\w.+-]+@[\\w-]+\\.[\\w.-]+$");

    //Longitud EXACTA de un hash BCrypt. Si el valor no mide 60 caracteres es
    //porque alguien intento guardar la contraseña sin cifrar.
    private static final int LONGITUD_CONTRASENA=60;

    @Override
    public void validarNombreObligatorio(String nombre) {
    
        if(nombre==null || nombre.isBlank()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El nombre de usuario es obligatorio");
        }

    }

    @Override
    public void validarCorreoFormato(String correo) {
       if(correo==null || !PATRON_CORREO.matcher(correo).matches()){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El correo no tiene un formato valido");

       }
    }

    @Override
    public void validarContrasenaLongitud(String contrasenaHash) {
       if(contrasenaHash==null || contrasenaHash.length() != LONGITUD_CONTRASENA){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "La contraseña no quedó cifrada correctamente: se esperaba un hash BCrypt de "
            + LONGITUD_CONTRASENA + " caracteres");

       }
    }

    @Override
    public void validarUsuario(Usuario usuario) {
        validarNombreObligatorio(usuario.getNombre());
        validarCorreoFormato(usuario.getCorreo());
        validarContrasenaLongitud(usuario.getContrasena_hash());

    }

}
