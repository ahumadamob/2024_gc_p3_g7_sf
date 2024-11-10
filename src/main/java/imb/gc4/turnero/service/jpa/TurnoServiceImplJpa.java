package imb.gc4.turnero.service.jpa;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import imb.gc4.turnero.entity.Turno;
import imb.gc4.turnero.repository.TurnoRepository;
import imb.gc4.turnero.service.ITurnoService;


@Service
@Primary
public class TurnoServiceImplJpa implements ITurnoService{

	@Autowired
	TurnoRepository repo;

	@Override
	public List<Turno> buscarTodos() {		
		return repo.findAll();
	}
	
	@Override
	public Turno buscarPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }	

	@Override
	public Turno guardar(Turno turno) {
		return repo.save(turno);
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);		
	}

	@Override
	public boolean existe(Integer id) {
	    return (id == null) ? false: repo.existsById(id);
	}
	
	@Override
    public List<Turno> filtrarPorFecha(LocalDateTime startDate, LocalDateTime endDate) {
        return repo.findByFechaYHoraBetween(startDate, endDate);
    }

	@Override
	public Turno cancelarTurno(Integer idTurno, String motivo) {
		Optional<Turno> turnoOpt = repo.findById(idTurno); 
        if (turnoOpt.isPresent()) {
            Turno turno = turnoOpt.get();
            turno.setEstado("CANCELADO");
            turno.setMotivoCancelacion(motivo);
            return repo.save(turno);
        } else {
            throw new NoSuchElementException("El turno con ID " + idTurno + " no fue encontrado.");
        }
    }
}
