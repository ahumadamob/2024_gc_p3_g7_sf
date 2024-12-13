package imb.gc4.turnero.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.gc4.turnero.entity.Mutual;
import imb.gc4.turnero.entity.Paciente;
import imb.gc4.turnero.repository.MutualRepository;
import imb.gc4.turnero.repository.PacienteRepository;
import imb.gc4.turnero.service.IPacienteService;


@Service
public class PacienteServiceImplJpa implements IPacienteService {
	
	@Autowired
	PacienteRepository repo;
	
    @Autowired
    MutualRepository mutualRepository;
	
	@Override
	public List<Paciente> buscarPacientes() {
		return repo.findAll();
	}

    @Override
    public Paciente buscarPacientePorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

	@Override
	public Paciente guardarPaciente(Paciente paciente) {
		return repo.save(paciente);
		
	}

	@Override
	public void eliminarPaciente(Integer id) {
		repo.deleteById(id);
		
	}
	
	@Override
	public boolean exists(Integer id) {
		return (id == null)? false: repo.existsById(id);
		
	}
	
	@Override
	public List<Paciente> filtrarPorNombre(String filtro){
		return repo.findByNombre(filtro);
	}
	
	@Override
	public Paciente actualizarEstado (Integer id, String estado) {
		Paciente paciente = buscarPacientePorId(id);
		paciente.setEstado(estado);
		return repo.save(paciente);
	}

	@Override
	public Paciente asignarProfesional(Long id, Long profesionalId) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public Paciente registrarPaciente(Paciente paciente) {
        // Validar que el nombre del paciente no esté vacío y no supere los 40 caracteres
        if (paciente.getNombre() == null || paciente.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del paciente no puede estar vacío.");
        }
        if (paciente.getNombre().length() > 40) {
            throw new IllegalArgumentException("El nombre del paciente no puede superar los 40 caracteres.");
        }

        // Validar que la mutual asociada exista y esté activa
        if (paciente.getMutual() == null || paciente.getMutual().getId() == null) {
            throw new IllegalArgumentException("Debe asociar una mutual válida al paciente.");
        }

        // Verificar si la mutual existe y si está activa
        Mutual mutual = mutualRepository.findById(paciente.getMutual().getId()).orElse(null);
        if (mutual == null) {
            throw new IllegalArgumentException("La mutual asociada no existe.");
        }
        if (!"ACTIVO".equalsIgnoreCase(mutual.getEstado())) {
            throw new IllegalArgumentException("La mutual asociada no está activa.");
        }

        // Guardar al paciente en la base de datos
        return repo.save(paciente);
    }
	

}

