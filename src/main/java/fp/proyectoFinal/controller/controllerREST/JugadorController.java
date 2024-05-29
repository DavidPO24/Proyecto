package fp.proyectoFinal.controller.controllerREST;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fp.proyectoFinal.model.Jugador;
import fp.proyectoFinal.repository.EquipoRepository;
import fp.proyectoFinal.repository.JugadorRepository;

@RestController
public class JugadorController{
	
	@Autowired
	private final JugadorRepository jugadorRepository;
	
	@Autowired
	private final EquipoRepository equipoRepository;

	public JugadorController(JugadorRepository jugadorRepository, EquipoRepository equipoRepository) {
		this.jugadorRepository = jugadorRepository;
		this.equipoRepository = equipoRepository;
	}
	
	@GetMapping("/jugador/modificar/{j}")
	public Jugador modificarJugador(@PathVariable int j) {
		Jugador p = jugadorRepository.getReferenceById(j);
		System.out.println(p);
		p.setEquipo(equipoRepository.getReferenceById(p.getEquipo().getIdEquipo()));
		Hibernate.initialize(p.getEquipo());
		return jugadorRepository.save(p);
	}
	
}
