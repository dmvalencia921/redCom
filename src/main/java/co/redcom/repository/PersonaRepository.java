package co.redcom.repository;

import co.redcom.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository  extends JpaRepository<Persona,Integer> {

    List<Persona> findByNombreIgnoreCase(String nombre);

    Persona findOneByNombreAndIdPersonaNot(String nombre, Integer idPersona);


}
