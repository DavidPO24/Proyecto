package fp.proyectoFinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fp.proyectoFinal.model.Jugador;

public interface JugadorRepository extends JpaRepository<Jugador, Integer> {

	@Query("SELECT j FROM Jugador j WHERE j.usuario.idusuario = ?1")
	Jugador buscarPorUsuario(int id);

	@Query("SELECT j FROM Jugador j WHERE j.equipo.idEquipo = ?1")
	List<Jugador> getJugadores(int id);

	@Query("SELECT j FROM Jugador j JOIN FETCH j.equipo WHERE j.idjugador = ?1")
	Jugador getJugador(int idjugador);
}
