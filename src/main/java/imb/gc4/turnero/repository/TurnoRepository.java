package imb.gc4.turnero.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.gc4.turnero.entity.Turno;


public interface TurnoRepository extends JpaRepository<Turno,Integer>{
	
	 List<Turno> findByFechaYHoraBetween(LocalDateTime startDate, LocalDateTime endDate);

}
