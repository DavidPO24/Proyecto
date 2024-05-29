package fp.proyectoFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fp.proyectoFinal.model.Tipousuario;

public interface TipoUsuarioRepository extends JpaRepository<Tipousuario, Integer>{

	@Query("SELECT tu FROM Tipousuario tu WHERE tu.id = ?1")
	Tipousuario getTipo(int id);

}
