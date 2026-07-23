package com.example.ReVueltaBack.validaciones.imagenprenda;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.modelos.ImagenPrenda;

@Component
public class ImagenesPrendaValidadorImpl implements IImagenesPrendaValidador {

    private static final int TAMANO_MAXIMO_KB = 5120;

    @Override
    public void validarUrl(ImagenPrenda imagenPrenda) {
        if (imagenPrenda.getUrl() == null || imagenPrenda.getUrl().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La URL de la imagen es obligatoria.");
        }
    }

    @Override
    public void validarTamano(ImagenPrenda imagenPrenda) {
        if (imagenPrenda.getTamaño_KB() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El tamaño de la imagen (KB) es obligatorio.");
        }
        if (imagenPrenda.getTamaño_KB() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El tamaño de la imagen debe ser mayor a 0 KB.");
        }
        if (imagenPrenda.getTamaño_KB() > TAMANO_MAXIMO_KB) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El tamaño de la imagen no puede superar " + TAMANO_MAXIMO_KB + " KB.");
        }
    }

    @Override
    public void validarFormato(ImagenPrenda imagenPrenda) {
        if (imagenPrenda.getFormato() == null || imagenPrenda.getFormato().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El formato de la imagen es obligatorio.");
        }
        String formato = imagenPrenda.getFormato().toLowerCase();
        if (!formato.equals("jpg") && !formato.equals("jpeg")
                && !formato.equals("png") && !formato.equals("webp")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Formato de imagen no soportado. Usa jpg, jpeg, png o webp.");
        }
    }

    @Override
    public void validar(ImagenPrenda imagenPrenda) {
        validarUrl(imagenPrenda);
        validarTamano(imagenPrenda);
        validarFormato(imagenPrenda);
    }
}