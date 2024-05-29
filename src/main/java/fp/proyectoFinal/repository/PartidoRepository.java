package fp.proyectoFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fp.proyectoFinal.model.Partido;
@Repository
public interface PartidoRepository extends JpaRepository<Partido, Integer>{

	@Query("SELECT p FROM Partido p WHERE p.idpartido = ?1")
	Partido getPartido(int id);

}
