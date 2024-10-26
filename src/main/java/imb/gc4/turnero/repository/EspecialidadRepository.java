package imb.gc4.turnero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.gc4.turnero.entity.Especialidad;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer> {
	
	List<Especialidad> findByEspecialidad(String especialidad);

}
