package fp.proyectoFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fp.proyectoFinal.model.Entrenador;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Integer>{

	@Query("SELECT e FROM Entrenador e WHERE e.equipo.idEquipo = ?1")
	Entrenador buscarPorEquipo(int id);

	@Query("SELECT e FROM Entrenador e WHERE e.usuario.idusuario = ?1")
	Entrenador buscarPorUsuario(int id);
}
