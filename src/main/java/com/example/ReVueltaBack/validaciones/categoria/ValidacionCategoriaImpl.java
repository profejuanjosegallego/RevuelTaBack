package com.example.ReVueltaBack.validaciones.categoria;

import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.modelos.Categoria;

@Component
public class ValidacionCategoriaImpl implements IValidacionCategoria {

    private static final Pattern PATRON_SLUG = Pattern.compile("^[a-z0-9]+(-[a-z0-9]+)*$");
    private static final int DESCRIPCION_MIN_LONGITUD = 3;
    private static final int DESCRIPCION_MAX_LONGITUD = 255;

    @Override
    public void validarNombreObligatorio(String nombre) {
        
        if (nombre == null || nombre.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre es obligatorio");
        }
    }

    @Override
    public void validarDescripcionLongitud(String descripcion) {
        
        if (descripcion == null || descripcion.length() < DESCRIPCION_MIN_LONGITUD || descripcion.length() > DESCRIPCION_MAX_LONGITUD) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La descripción debe tener entre 3 y 255 caracteres.");
        }
    }

    @Override
    public void validarSlugFormato(String slug) {
        
        if (slug == null || !PATRON_SLUG.matcher(slug).matches())
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "validarSlugFormato");
    }

    @Override
    public void validarCategoria(Categoria categoria) {
        
        validarNombreObligatorio(categoria.getNombre());
        validarDescripcionLongitud(categoria.getDescripcion());
        validarSlugFormato(categoria.getSlug());
    }
    
}
