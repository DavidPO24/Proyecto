package fp.proyectoFinal.controller.controllerREST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fp.proyectoFinal.model.Equipo;
import fp.proyectoFinal.model.Jugador;
import fp.proyectoFinal.repository.EquipoRepository;
import fp.proyectoFinal.repository.JugadorRepository;

@RestController
public class EquipoController {
	
	@Autowired
	private final JugadorRepository jugadorRepository;
	
	@Autowired
	private final EquipoRepository equipoRepository;
	
	public EquipoController(JugadorRepository jR, EquipoRepository equipoRepository) {
		this.jugadorRepository = jR;
		this.equipoRepository = equipoRepository;
	}

	@Transactional
	@GetMapping("/equipo/{id}")
	public List<Jugador> players(@PathVariable("id") int id){
		return jugadorRepository.getJugadores(id);
	}
	
	@GetMapping("/equipo/crear/{nombre}")
	public void crearEquipo(@PathVariable("nombre") String nombre) {
		equipoRepository.save(new Equipo(nombre));
	}

}
