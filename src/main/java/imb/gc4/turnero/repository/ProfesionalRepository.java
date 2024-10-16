package imb.gc4.turnero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.gc4.turnero.entity.Profesional;

public interface ProfesionalRepository extends JpaRepository<Profesional, Integer> {
	
	List<Profesional> findAllByOrderByApellidoProfesionalAsc();
	List<Profesional> findByNombreProfesional(String nombre);
}