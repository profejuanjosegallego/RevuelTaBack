package com.example.ReVueltaBack.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReVueltaBack.servicios.Usuario.IServicioUsuario;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {
    private final IServicioUsuario servicioUsuario;
    
}
