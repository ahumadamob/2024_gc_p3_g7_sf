package imb.gc4.turnero.service;

import java.util.List;

import imb.gc4.turnero.entity.Profesional;

public interface IProfesionalService {
	public List<Profesional> buscar();
	public Profesional buscarPorId(Integer id);
	public Profesional guardar(Profesional profesional);
	public void eliminar(Integer id);
	public boolean exists(Integer id);
	public List<Profesional> buscarOrdenadosPorApellido();
	public List<Profesional> filtrarPorNombre(String filtro);
}

