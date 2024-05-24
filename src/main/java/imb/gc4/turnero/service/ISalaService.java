package imb.gc4.turnero.service;

import java.util.List;

import imb.gc4.turnero.entity.Sala;

public interface ISalaService {
	public Sala mostrarPorId(Integer id);
	public Sala guardar(Sala sala);
	public void eliminar(Integer id);
	public List<Sala> mostrarTodos();
	public boolean exist(Integer id);
	public List<Sala>mostrarPorDisponibles(boolean disponible);
	public List<Sala> getSalasOrdenadasPorNombre();
	
}
