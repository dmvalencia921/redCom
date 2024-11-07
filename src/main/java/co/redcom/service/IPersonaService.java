package co.redcom.service;

import co.redcom.entity.Persona;

import java.util.List;

public interface IPersonaService {
    Persona crearPersona(Persona persona);
    List<Persona> listarPersonas();
    Persona actualizarPersona(Persona persona);
    void eliminarPersona(Integer idPersona);
}
