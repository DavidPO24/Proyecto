package fp.proyectoFinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fp.proyectoFinal.model.Partido;
@Repository
public interface PartidoRepository extends JpaRepository<Partido, Integer>{

	@Query("SELECT p FROM Partido p WHERE p.idpartido = ?1")
	Partido getPartido(int id);
	
	@Query("SELECT p FROM Partido p WHERE p.equipoVisitante.idEquipo = ?1")
	List<Partido> getVisitantes(int id);

	@Query("SELECT p FROM Partido p WHERE p.equipoLocal.idEquipo = ?1")
	List<Partido> getLocal(int id);

}
