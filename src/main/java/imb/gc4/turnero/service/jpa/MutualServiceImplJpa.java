package imb.gc4.turnero.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.gc4.turnero.entity.Mutual;
import imb.gc4.turnero.repository.MutualRepository;
import imb.gc4.turnero.service.IMutualService;


@Service
public class MutualServiceImplJpa implements IMutualService {

	@Autowired
	MutualRepository repo;
	
	@Override
	public List<Mutual> obtenerTodas() {
		return repo.findAll();
	}

	@Override
    public Mutual obtenerPorId(Integer id) {
        Optional<Mutual> optional = repo.findById(id);
        return optional.orElse(null);
    }

	@Override
	public Mutual guardar(Mutual mutual) {
		return repo.save(mutual);
	}

	
	@Override
	public boolean exists(Integer id) {
		return (id==null)? false: repo.existsById(id);
	}
	@Override
	public void eliminar(Integer id) {
	repo.deleteById(id);
	}

	@Override
	public boolean existsBeneficio(String beneficio) {
		if (beneficio == null) {
            return false;
        }
        List<Mutual> mutuales = repo.findByBeneficiosContains(beneficio);
        return !mutuales.isEmpty();
	}

	@Override
	public List<Mutual> filtrarPorBeneficio(String beneficio) {
		return repo.findByBeneficiosContains(beneficio);
	}

	@Override
	public List<Mutual> filtrarPorMutual(String mutual) {
		return repo.findByBeneficiosContains(mutual);
	}


}
