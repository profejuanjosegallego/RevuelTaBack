package com.example.ReVueltaBack.repositorios;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ReVueltaBack.modelos.Usuario;

// HU USR-R01 — Repositorio JPA de Usuario, orientado al login.
// Al extender JpaRepository ya se hereda el CRUD (save, findById, findAll, deleteById);
// aqui solo se agregan las consultas propias de la autenticacion.
@Repository
public interface IUsuarioRepositorio extends JpaRepository<Usuario, UUID> {

    // Clave para autenticar en el login: buscar al usuario por su correo.
    Optional<Usuario> findByCorreo(String correo);

    // Valida que el correo no exista al registrar (correo unico).
    boolean existsByCorreo(String correo);

    // Lista solo los usuarios activos.
    List<Usuario> findByActivoTrue();

    // Filtra por rol (docente / estudiante).
    List<Usuario> findByRol(String rol);

    // Consulta JPQL: busca el correo sin distinguir mayusculas de minusculas.
    @Query("SELECT u FROM Usuario u WHERE LOWER(u.correo) = LOWER(:correo)")
    Optional<Usuario> buscarPorCorreo(@Param("correo") String correo);
}
