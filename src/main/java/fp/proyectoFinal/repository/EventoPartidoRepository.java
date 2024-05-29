package fp.proyectoFinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fp.proyectoFinal.model.EventoPartido;

public interface EventoPartidoRepository extends JpaRepository<EventoPartido, Integer> {
	
	@Query("SELECT COUNT(ep) FROM EventoPartido ep WHERE ep.partido.idpartido = ?1")
	int hayDatos(int id);

	@Query("SELECT ep FROM EventoPartido ep WHERE ep.partido.idpartido = ?1")
	List<EventoPartido> findByPartido(int id);
}
