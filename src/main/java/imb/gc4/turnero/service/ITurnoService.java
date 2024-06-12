package imb.gc4.turnero.service;

import java.time.LocalDateTime;
import java.util.List;

import imb.gc4.turnero.entity.Turno;

public interface ITurnoService {
	
	public List<Turno> buscarTodos ();
	public Turno buscarPorId (Integer id);
	public Turno guardar (Turno turno);
	public void eliminar (Integer id);
	public boolean existe (Integer id);
	public List<Turno> filtrarPorFecha(LocalDateTime startDate, LocalDateTime endDate);

}
