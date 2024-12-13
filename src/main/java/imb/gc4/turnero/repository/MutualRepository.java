package imb.gc4.turnero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.gc4.turnero.entity.Mutual;


public interface MutualRepository extends JpaRepository<Mutual, Integer> {
	
	List<Mutual> findByBeneficiosContains(String beneficios);
	// Verificar si una mutual con un ID específico tiene el estado "ACTIVO"
    boolean existsByIdAndEstadoIgnoreCase(Integer id, String estado);
	
}
