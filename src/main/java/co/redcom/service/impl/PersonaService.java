package co.redcom.service.impl;

import co.redcom.entity.Persona;
import co.redcom.repository.PersonaRepository;
import co.redcom.service.IPersonaService;
import co.redcom.util.constants.Constants;
import co.redcom.util.utilities.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PersonaService implements IPersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    private final String classLog = getClass().getName()+ '.';


    @Override
    public Persona crearPersona(Persona persona) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "crearPersona");
        if(Validation.isNullOrEmpty(personaRepository.findByNombreIgnoreCase(persona.getNombre()))){
            persona.setFechaCreacion(new Date());
            Persona newPersona = personaRepository.save(persona);
            if(!Validation.isNullOrEmpty(newPersona)){
                return newPersona;
            }
            log.error(Constants.MSN_FIN_LOG_INFO + classLog + "No se puede crear la persona");
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, " No se puede crear la persona");
        }
        log.info(Constants.MSN_FIN_LOG_INFO+classLog + "Persona creada");
        throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "La persona ya  creada");
    }

    @Override
    public List<Persona> listarPersonas() {
        List<Persona> listaPersonas = personaRepository.findAll();
        if(!Validation.isNullOrEmpty(listaPersonas)){
            return listaPersonas;
        }
       throw  new  ResponseStatusException(HttpStatus.BAD_REQUEST, "La lista de personas esta vacia");
    }

    @Override
    public Persona actualizarPersona(Persona persona) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "actualizarPersona");
        Optional<Persona> lista = personaRepository.findById(persona.getIdPersona());
        if(lista.isPresent()){
            if(Validation.isNullOrEmpty(personaRepository.findOneByNombreAndIdPersonaNot(persona.getNombre(), persona.getIdPersona()))){
                persona.setFechaModificacion(new Date());
                personaRepository.save(persona);
                return  persona;
            }
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "No se pudo actualizar la persona");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La persona ya existe");
        }
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "actualizarPersona");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El registro no existe");
    }

    @Override
    public void eliminarPersona(Integer idPersona) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "eliminarPersona");
        Optional<Persona> lista = personaRepository.findById(idPersona);
        if(lista.isEmpty()){
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminarPersona");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La persona no existe");
        }
        personaRepository.deleteById(idPersona);
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminarPersona");
    }
}
