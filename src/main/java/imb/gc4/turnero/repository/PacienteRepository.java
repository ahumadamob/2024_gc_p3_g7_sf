package imb.gc4.turnero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.gc4.turnero.entity.Paciente;


public interface PacienteRepository extends JpaRepository<Paciente, Integer>{
	
}
