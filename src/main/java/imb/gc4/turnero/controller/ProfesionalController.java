package imb.gc4.turnero.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.gc4.turnero.entity.Paciente;
import imb.gc4.turnero.entity.Profesional;
import imb.gc4.turnero.exception.ProfesionalExc;
import imb.gc4.turnero.service.IProfesionalService;
import imb.gc4.turnero.util.APIResponse;
import imb.gc4.turnero.util.ResponseUtil;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/v1/profesional")
public class ProfesionalController {
	
	@Autowired
	IProfesionalService profesionalService;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Profesional>>> mostrarTodosLosProfesionales() {
		List<Profesional> profesional = profesionalService.buscar();
		if(profesional.isEmpty()) {
			return ResponseUtil.notFound("No se encontraron Profesionales");
		}else {
			return ResponseUtil.success(profesional);
		}
	}
	
	
	 @GetMapping("/ordenado")
	    public ResponseEntity<APIResponse<List<Profesional>>> buscarOrdenadosPorApellido() {
	        List<Profesional> profesionales = profesionalService.buscarOrdenadosPorApellido();
	        
	        if (profesionales != null && !profesionales.isEmpty()) {
	            profesionales.sort(Comparator.comparing(Profesional::getApellidoProfesional));
	        }
	        
	        APIResponse<List<Profesional>> response = new APIResponse<>(200, null, profesionales);
	        return ResponseEntity.status(HttpStatus.OK).body(response);
	    } 
	 

	
	// Esta es una anotacion que indica que responde a una solicitud http con un marcador de posicion (ID)
	@GetMapping("/{id}")
	// Este metodo recibe un parametro id que proviene de la url gracias al parametro @PathVariable("id")
	public ResponseEntity<APIResponse<Profesional>> buscarProfesionalPorId(@PathVariable("id") Integer id) {
		//En este ternario Primero se busca al profesional en la base de dato para ver si existe, si existe retorna al profesional que coincida con el id sino devuelve un msj 
		return (profesionalService.exists(id) ? ResponseUtil.success(profesionalService.buscarPorId(id))
				: ResponseUtil.notFound("No se encontro el profesional"));
	}
	
	@GetMapping("/filtrar/{nombre}")
	public ResponseEntity<APIResponse<List<Profesional>>> buscarProfesionalPorNombre(@PathVariable("nombre") String nombre){
		List<Profesional> filtrar = profesionalService.filtrarPorNombre(nombre);
		return (filtrar.isEmpty()) ? ResponseUtil.notFound("No se ha encontrado a nadie con ese nombre") 
				: ResponseUtil.success(profesionalService.filtrarPorNombre(nombre));
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<Profesional>> crearProfesional(@Valid @RequestBody Profesional profesional, BindingResult result) {
		if(result.hasErrors()) {
			throw new ProfesionalExc("Error en la validacion");
		}else {
		return (profesionalService.exists(profesional.getId()))? ResponseUtil.badRequest("Ya Existe un Profesor")
				: ResponseUtil.created(profesionalService.guardar(profesional));			
		}
	}
		
	
	@PutMapping	
	public ResponseEntity<APIResponse<Profesional>> modificarProfesional(@Valid @RequestBody Profesional profesional, BindingResult result) {
		if(result.hasErrors()) {
			throw new ProfesionalExc("Error en la validacion");
		}else {
		return (profesionalService.exists(profesional.getId()))? ResponseUtil.created(profesionalService.guardar(profesional))
				:ResponseUtil.notFound("No existe un profesional con el ID identificado");
		}
	}
	
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<String>> eliminarProfesional(@PathVariable("id") Integer id) {
	    if(profesionalService.exists(id)){
	    	profesionalService.eliminar(id);
	        return ResponseUtil.success("El Profesional fue eliminado");
	    }else {
	        return ResponseUtil.badRequest("No existe una Profesional con el Id especificado");
	    }
	}
	
	
	
}


	