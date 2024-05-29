package fp.proyectoFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fp.proyectoFinal.model.Tipoevento;

public interface TipoeventoRepository extends JpaRepository<Tipoevento, Integer> {

	@Query("SELECT te FROM Tipoevento te WHERE te.idtipoEvento = ?1")
	Tipoevento getTipoEvento(int idtipoEvento);

}
