package imb.gc4.turnero.service;

import java.util.List;

import imb.gc4.turnero.entity.Paciente;

public interface IPacienteService {
	
	public List<Paciente> buscarPacientes();
	public Paciente buscarPacientePorId(Integer id);
	public Paciente guardarPaciente(Paciente paciente);
	public void eliminarPaciente(Integer id);
	public boolean exists(Integer id);

	public List<Paciente> filtrarPorNombre(String filtro);
	public Paciente actualizarEstado (Integer id, String estado);
	public Paciente asignarProfesional(Long id, Long profesionalId);
}
