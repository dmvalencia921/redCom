package co.redcom.restController;

import co.redcom.entity.Persona;
import co.redcom.service.impl.PersonaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persona")
public class PersonaRestController {

    @Autowired
    private PersonaService personaService;

    @PostMapping("/crearPersona")
    @Operation(summary = "Crear persona", description = "Este método permite crear una persona")
    public ResponseEntity<Persona> crearPersona(@RequestBody  Persona persona) {
        return ResponseEntity.ok(personaService.crearPersona(persona));
    }

    @GetMapping("/listarPersonas")
    @Operation(summary = "Listar personas", description = "Este método permite listar las personas")
    public ResponseEntity<List<Persona>> listarPersonas() {
       return  ResponseEntity.ok(personaService.listarPersonas());
    }

    @PutMapping("/actualizarPersona")
    @Operation(summary = "Actualizar persona", description = "Este método permite actualizar una persona")
    public ResponseEntity<Persona> actualizarPersona(@RequestBody Persona persona) {
        return  ResponseEntity.ok(personaService.actualizarPersona(persona));
    }

    @DeleteMapping("/eliminarPersona/{idPersona}")
    @Operation(summary = "Eliminar persona", description = "Este método permite eliminar persona")
    public void eliminarPersona(@PathVariable Integer idPersona){
        personaService.eliminarPersona(idPersona);
    }
}
