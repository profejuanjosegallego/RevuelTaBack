package com.example.ReVueltaBack.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import com.example.ReVueltaBack.modelos.Reseñas;
import com.example.ReVueltaBack.modelos.Usuario;

import jakarta.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ReseñasRepositoryTest {

    @Autowired
    private ReseñaRepository reseñasRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void deberiaBuscarPorAutorYUsuarioResenadoYPorTituloYOrdenYQueryJpa() {
        Usuario autor = new Usuario();
        Usuario usuarioResenado = new Usuario();
        entityManager.persist(autor);
        entityManager.persist(usuarioResenado);

        Reseñas reseña1 = crearReseña("Muy buena atención", "Atención", LocalDate.of(2026, 7, 1), true, autor, usuarioResenado);
        Reseñas reseña2 = crearReseña("El lugar está bien", "Lugar", LocalDate.of(2026, 7, 5), false, autor, usuarioResenado);
        Reseñas reseña3 = crearReseña("Otro comentario", "Servicio", LocalDate.of(2026, 6, 20), true, usuarioResenado, autor);

        reseñasRepository.saveAll(List.of(reseña1, reseña2, reseña3));
        reseñasRepository.flush();

        assertThat(reseñasRepository.findByAutor(autor)).hasSize(2);
        assertThat(reseñasRepository.findByUsuarioResenado(usuarioResenado)).hasSize(2);
        assertThat(reseñasRepository.findByTituloContainingIgnoreCase("atencion")).hasSize(1);
        assertThat(reseñasRepository.findByRecomendadoTrue()).hasSize(2);
        assertThat(reseñasRepository.findAllByOrderByFechaDesc()).extracting(Reseñas::getFecha)
                .containsExactly(LocalDate.of(2026, 7, 5), LocalDate.of(2026, 7, 1), LocalDate.of(2026, 6, 20));
        assertThat(reseñasRepository.buscarPorUsuariosId(autor.getId())).hasSize(2);
    }

    private Reseñas crearReseña(String comentario, String titulo, LocalDate fecha, Boolean recomendado, Usuario autor,
            Usuario usuarioResenado) {
        Reseñas reseña = new Reseñas();
        reseña.setComentario(comentario);
        reseña.setTitulo(titulo);
        reseña.setFecha(fecha);
        reseña.setRecomendado(recomendado);
        reseña.setEditada(false);
        reseña.setVisible(true);
        reseña.setAutor(autor);
        reseña.setUsuarioResenado(usuarioResenado);
        return reseña;
    }
}