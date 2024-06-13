package imb.gc4.turnero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.gc4.turnero.entity.Paciente;


public interface PacienteRepository extends JpaRepository<Paciente, Integer>{
	
	public List<Paciente> filtrarPaciente(String filtro);
}
