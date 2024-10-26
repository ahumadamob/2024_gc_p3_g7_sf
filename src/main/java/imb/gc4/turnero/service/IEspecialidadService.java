package imb.gc4.turnero.service;

import java.util.List;

import imb.gc4.turnero.entity.Especialidad;

public interface IEspecialidadService {
	
	public List<Especialidad> obtenerTodas();
	public Especialidad obtenerPorId(Integer id);
	public Especialidad guardar(Especialidad especialidad);
	public void eliminar(Integer id);
	public boolean exists (Integer id);
	public List<Especialidad> filtrarPorEsecialidad(String especialidad);

}
