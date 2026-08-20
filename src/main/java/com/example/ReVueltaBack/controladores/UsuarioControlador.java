package com.example.ReVueltaBack.controladores;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReVueltaBack.dtos.usuario.UsuarioRequestDTO;
import com.example.ReVueltaBack.dtos.usuario.UsuarioResponseDTO;
import com.example.ReVueltaBack.servicios.usuario.IServicioUsuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

// HU USR-C01 / USR-O01 — Controlador REST de Usuario, documentado con OpenAPI.
// El controlador NO tiene logica: recibe la peticion, llama al servicio y
// traduce el resultado a una respuesta HTTP con el codigo correcto.
// Documentacion interactiva en: http://localhost:8080/swagger-ui.html
@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Modulo comun de Autenticacion: registro y gestion de usuarios")
public class UsuarioControlador {

    private final IServicioUsuario servicioUsuario;

    // Inyeccion por constructor: Spring entrega la implementacion del servicio.
    public UsuarioControlador(IServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    @PostMapping
    @Operation(summary = "Registrar un usuario",
               description = "Crea un usuario nuevo. La contrasena viaja en texto plano y el servidor la cifra con BCrypt antes de guardarla.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Usuario creado",
                     content = @Content(schema = @Schema(implementation = UsuarioResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos invalidos (nombre vacio, correo mal formado o contrasena corta)",
                     content = @Content),
        @ApiResponse(responseCode = "409", description = "Ya existe un usuario con ese correo", content = @Content)
    })
    public ResponseEntity<UsuarioResponseDTO> registrar(@RequestBody UsuarioRequestDTO datos) {
        UsuarioResponseDTO usuarioCreado = servicioUsuario.registrar(datos);
        // 201 Created es el codigo correcto al crear un recurso (no 200 OK).
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
    }

    @GetMapping
    @Operation(summary = "Listar usuarios", description = "Devuelve todos los usuarios registrados, sin exponer sus contrasenas.")
    @ApiResponse(responseCode = "200", description = "Listado de usuarios")
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        return ResponseEntity.ok(servicioUsuario.listar());
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar un usuario por correo",
               description = "Consulta usada por el login. No distingue mayusculas de minusculas.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "No hay usuario con ese correo", content = @Content)
    })
    public ResponseEntity<UsuarioResponseDTO> buscarPorCorreo(
            @Parameter(description = "Correo a buscar", example = "juan.gallego@cesde.edu.co")
            @RequestParam String correo) {
        return ResponseEntity.ok(servicioUsuario.buscarPorCorreo(correo));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un usuario por id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
    })
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(
            @Parameter(description = "Identificador UUID del usuario", example = "3f2504e0-4f89-11d3-9a0c-0305e82c3301")
            @PathVariable UUID id) {
        return ResponseEntity.ok(servicioUsuario.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un usuario",
               description = "Actualiza nombre, correo, rol, estado y color de avatar. La contrasena NO se cambia aqui.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario actualizado"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content),
        @ApiResponse(responseCode = "409", description = "El correo nuevo ya pertenece a otro usuario", content = @Content)
    })
    public ResponseEntity<UsuarioResponseDTO> actualizar(
            @PathVariable UUID id,
            @RequestBody UsuarioRequestDTO datos) {
        return ResponseEntity.ok(servicioUsuario.actualizar(id, datos));
    }

    @PatchMapping("/{id}/contrasena")
    @Operation(summary = "Cambiar la contrasena de un usuario",
               description = "Recibe la contrasena nueva en texto plano y guarda su hash BCrypt.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Contrasena actualizada"),
        @ApiResponse(responseCode = "400", description = "La contrasena nueva es muy corta o esta vacia", content = @Content),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
    })
    public ResponseEntity<UsuarioResponseDTO> cambiarContrasena(
            @PathVariable UUID id,
            @Parameter(description = "Contrasena nueva, minimo 8 caracteres", example = "MiClaveSegura123")
            @RequestParam String contrasenaNueva) {
        return ResponseEntity.ok(servicioUsuario.cambiarContrasena(id, contrasenaNueva));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Usuario eliminado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
    })
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        servicioUsuario.eliminar(id);
        // 204 No Content: se borro y no hay cuerpo que devolver.
        return ResponseEntity.noContent().build();
    }
}
