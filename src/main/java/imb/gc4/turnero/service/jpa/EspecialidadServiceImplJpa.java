package imb.gc4.turnero.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.gc4.turnero.entity.Especialidad;
import imb.gc4.turnero.repository.EspecialidadRepository;
import imb.gc4.turnero.service.IEspecialidadService;

@Service
public class EspecialidadServiceImplJpa implements IEspecialidadService {
	
	@Autowired
	EspecialidadRepository repo;

	@Override
	public List<Especialidad> obtenerTodas() {
		
		return repo.findAll();
	}
	
	@Override
	public Especialidad guardar(Especialidad especialidad) {
		return repo.save(especialidad);
		
	}

	 @Override
	    public Especialidad obtenerPorId(Integer id) {
	        Optional<Especialidad> optional = repo.findById(id);
	        return optional.orElse(null);
	    }

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);				
	}
	
	public boolean exists (Integer id) {
		return (id == null)? false : repo.existsById(id);
	}

	@Override
	public List<Especialidad> filtrarPorNombre(String nombre) {
	
		return repo.findByNombre(nombre);
	}
	
}
