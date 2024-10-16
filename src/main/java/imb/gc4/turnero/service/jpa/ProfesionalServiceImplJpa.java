package imb.gc4.turnero.service.jpa;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import imb.gc4.turnero.entity.Paciente;
import imb.gc4.turnero.entity.Profesional;
import imb.gc4.turnero.repository.ProfesionalRepository;
import imb.gc4.turnero.service.IProfesionalService;


@Service
@Primary
public class ProfesionalServiceImplJpa implements IProfesionalService {
	
	@Autowired
	ProfesionalRepository repo;
	 


	@Override
	public List<Profesional> buscar() {		
		return repo.findAll();

	}

	@Override
	public Profesional guardar(Profesional profesional) {
		return repo.save(profesional);
		
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);		
	}

	@Override
	public Profesional buscarPorId(Integer id) {
		Optional<Profesional> optional = repo.findById(id);
		return optional.orElse(null);	
	}
	
	@Override
	public boolean exists(Integer id) {
		return(id == null)? false : repo.existsById(id);
	}

	@Override
	public List<Profesional> buscarOrdenadosPorApellido() {
        return repo.findAllByOrderByApellidoProfesionalAsc();
    }
	
	@Override
	public List<Profesional> filtrarPorNombre(String nombre){
		return repo.findByNombreProfesional(nombre);
	}

}
