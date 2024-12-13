package imb.gc4.turnero.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.gc4.turnero.entity.Mutual;
import imb.gc4.turnero.entity.Paciente;
import imb.gc4.turnero.entity.Profesional;
import imb.gc4.turnero.exception.PacienteException;
import imb.gc4.turnero.repository.PacienteRepository;
import imb.gc4.turnero.repository.ProfesionalRepository;
import imb.gc4.turnero.service.IPacienteService;
import imb.gc4.turnero.util.APIResponse;
import imb.gc4.turnero.util.ResponseUtil;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/paciente")
public class PacienteController {
	@Autowired
    private PacienteRepository pacienteRepository;

    private final ProfesionalRepository profesionalRepository;

	@Autowired
	IPacienteService pacienteServicio;
	
	

    PacienteController(ProfesionalRepository profesionalRepository) {
        this.profesionalRepository = profesionalRepository;
    }

	@GetMapping
	public ResponseEntity<APIResponse<List<Paciente>>> mostrarTodos() {
		List<Paciente> pacientes = pacienteServicio.buscarPacientes();
		if (pacientes.isEmpty()) {
			return ResponseUtil.notFound("No se encontraron pacientes.");
		} else {
			return ResponseUtil.success(pacientes);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Paciente>> mostrarPacientePorId(@PathVariable("id") Integer id) {
		return (pacienteServicio.exists(id)) ? ResponseUtil.success(pacienteServicio.buscarPacientePorId(id))
				: ResponseUtil.notFound("No se encontró el paciente con id = " + id.toString() + ".");

	}

	@GetMapping("/nombre/{filtro}")
	public ResponseEntity<APIResponse<List<Paciente>>> buscarPacientePorNombre(@PathVariable("filtro") String filtro) {
		List<Paciente> filtrar = pacienteServicio.filtrarPorNombre(filtro);
		if (filtrar.isEmpty()) {
			return ResponseUtil.notFound("No se encontro");
		} else {
			return ResponseUtil.success(pacienteServicio.filtrarPorNombre(filtro));
		}
	}

	// Con la notación @PostMapping indicamos que el siguiente método recibirá
	// solicitudes HTTP POST.


	// El método llamado 'crearPaciente' recibe un objeto 'Paciente', el cual se
	// incluirá en el cuerpo de la solicitud HTTP.
	// Además, el método devuelve un 'ResponseEntity' (que contiene un objeto de
	// tipo 'APIResponse') parametrizado con 'Paciente'.
	
	
	
	// Verificamos si existe un paciente en función de su ID, en caso de existir,
			// devolvemos una respuesta d he error,
			// si aún no existe, lo guardamos en el método 'guardarPaciente' y devolvemos
			// una respuesta existosa mediante
			// el 'ResponseUtil.created(...)'.
	
	

	@PostMapping("/{id}/asignar-profesional")
	public ResponseEntity<APIResponse<Paciente>> asignarProfesional(
	        @PathVariable("id") Integer pacienteId,
	        @RequestBody Map<String, Long> request) {
	    
	    Long profesionalId = request.get("profesionalId");

	    // Validar existencia del paciente
	    if (!pacienteRepository.existsById(pacienteId)) {
	        return ResponseUtil.notFound("No se encontró el paciente con id = " + pacienteId);
	    }

	    // Validar existencia del profesional
	    if (!profesionalRepository.existsById(profesionalId.intValue())) {
	        return ResponseUtil.notFound("No se encontró el profesional con id = " + profesionalId);
	    }

	    // Obtener el paciente y el profesional para asignación
	    Paciente paciente = pacienteRepository.findById(pacienteId).get();
	    Profesional profesional = profesionalRepository.findById(profesionalId.intValue()).get();

	    // Asignar profesional al paciente
	    paciente.setProfesional(profesional);
	    pacienteRepository.save(paciente);

	    return ResponseUtil.success(paciente);
	}
	
	

	@PostMapping("/registrar")
	public ResponseEntity<APIResponse<Paciente>> registrarPaciente(@RequestBody Paciente paciente) {
	    // Llamada al servicio para registrar el paciente
	    Paciente pacienteRegistrado = pacienteServicio.registrarPaciente(paciente);
	    
	    // Si la respuesta es exitosa, se retorna con un código 200
	    return ResponseUtil.success(pacienteRegistrado);
	}
	
	
	
	@PostMapping
	public ResponseEntity<APIResponse<Paciente>> crearPaciente(@Valid @RequestBody Paciente paciente, BindingResult result) {
		if(result.hasErrors()) {
			 throw new PacienteException("Error en la validación de los datos del paciente.");
		}else{
               return (pacienteServicio.exists(paciente.getId())) ? ResponseUtil.badRequest("Ya existe un paciente.")
				: ResponseUtil.created(pacienteServicio.guardarPaciente(paciente));
		}
		}

	@PutMapping
	public ResponseEntity<APIResponse<Paciente>> modificarPaciente(@RequestBody Paciente paciente) {
		if (pacienteServicio.exists(paciente.getId())) {
			return ResponseUtil.created(pacienteServicio.guardarPaciente(paciente));
		} else if (paciente.getId() == null) {
			return ResponseUtil.badRequest("No ingresaste id de paciente para modificarlo.");
		} else {
			return ResponseUtil.badRequest("No existe un paciente con el id = " + paciente.getId().toString() + ".");
		}
		
		}
	@PutMapping("/actualizar-estado")
	public ResponseEntity<APIResponse<Paciente>> actualizarEstadoPaciente(@RequestBody Paciente paciente){
		if(pacienteServicio.exists(paciente.getId()) && paciente.getEstado() != null) {
			return ResponseUtil.created(pacienteServicio.actualizarEstado(paciente.getId(), paciente.getEstado()));
		}else if (!pacienteServicio.exists(paciente.getId())){
			return ResponseUtil.notFound("No existe un paciente con el id = " + paciente.getId().toString() + ".");
		}else {
			return ResponseUtil.badRequest("Debe escribir el id y el estado para actualizar");
		}
	}	


	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<String>> eliminarPaciente(@PathVariable("id") Integer id) {
		if (pacienteServicio.exists(id)) {
			pacienteServicio.eliminarPaciente(id);
			return ResponseUtil.success("El paciente con id = " + id.toString() + " ha sido eliminado.");
		} else {
			return ResponseUtil.badRequest("No existe un paciente con el id = " + id.toString() + ".");
		}
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Object>> handleConstrainViolationException(ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
	}
}
