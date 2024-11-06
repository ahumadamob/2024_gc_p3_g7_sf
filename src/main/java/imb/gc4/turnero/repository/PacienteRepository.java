package imb.gc4.turnero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.gc4.turnero.entity.Paciente;


public interface PacienteRepository extends JpaRepository<Paciente, Integer /*mismo tipo de dato que el id*/>{
	
	public List<Paciente> findByNombre(String filtro);
}
