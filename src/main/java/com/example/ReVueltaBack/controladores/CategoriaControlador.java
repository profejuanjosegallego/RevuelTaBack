import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReVueltaBack.dtos.Categoria.CategoriaRequestDTO;
import com.example.ReVueltaBack.dtos.Categoria.CategoriaResponseDTO;
import com.example.ReVueltaBack.servicios.Categoria.IServicioCategoria;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaControlador{

    private final IservicioCategoria servicioCategoria;

    public CategoriaControlador(IServicioCategoria servicioUsuario) {
        this.servicioCategoria = servicioCategoria;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO>registrar(@RequestBody CategoriaRequestDTO datos){
        CategoriaResponseDTO categoriaCreada=servicioCategoria.registrar(datos);
        return ResponseEntity.ok(usurioCreado);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>>listar(){
        return ResponseEntity.ok(servicioCategoria.listar());
    }

     @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO>buscarPorId(@PathVariable UUID id){
        return ResponseEntity.ok(servicioCategoria.buscarPorId(id));
    }


        @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO>actualizar(@RequestBody UsuarioRequestDTO datos, @PathVariable UUID id){
        return ResponseEntity.ok(servicioCategoria.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>eliminar(@PathVariable UUID id){
        servicioCategoria.eliminar(id);
        return ResponseEntity.noContent().build();

    }


}