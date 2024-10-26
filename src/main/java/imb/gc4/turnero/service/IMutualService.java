package imb.gc4.turnero.service;

import java.util.List;

import imb.gc4.turnero.entity.Mutual;

public interface IMutualService {

	public List<Mutual> obtenerTodas();
	public Mutual obtenerPorId(Integer id);
	public Mutual guardar(Mutual mutual);
	public boolean exists(Integer id);
	public boolean existsBeneficio(String beneficio);
	public void eliminar(Integer id);
	public List<Mutual> filtrarPorBeneficio(String beneficio);
	public List<Mutual> filtrarPorMutual(String mutual);
	
}
