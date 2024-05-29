package fp.proyectoFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fp.proyectoFinal.model.Entrenador;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Integer>{

}
