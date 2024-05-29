package fp.proyectoFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import fp.proyectoFinal.model.Equipo;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {

	@Query("SELECT e FROM Equipo e WHERE e.idEquipo = ?1")
	Equipo getEquipo(int id);

}
