package imb.gc4.turnero.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import imb.gc4.turnero.entity.Sala;
import imb.gc4.turnero.repository.SalaRepository;
import imb.gc4.turnero.service.ISalaService;

@Service
@Primary
public class SalaServiceImplJpa implements ISalaService {
	
	@Autowired
	SalaRepository repo;

	@Override
	public List<Sala> mostrarTodos() {		
		return repo.findAll();

	}

	@Override
	public Sala guardar(Sala sala) {
		return repo.save(sala);
		
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);		
	}
	
	@Override
	public boolean exist(Integer id) {
		return (id == null)? false: repo.existsById(id);
	}

	@Override
	public Sala mostrarPorId(Integer id) {
		Optional<Sala> optional = repo.findById(id);
		return optional.orElse(null);		
	}

	@Override
	public List<Sala> mostrarPorDisponibles(boolean disponible) {
		return repo.findByDisponibles(disponible);
	}
	
	@Override
	public List<Sala> findAllByOrderByNombreAsc(){
		return repo.findAllByOrderByNombreAsc();
	}

}
