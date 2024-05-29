package fp.proyectoFinal.controller.controllerREST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fp.proyectoFinal.model.Jugador;
import fp.proyectoFinal.repository.JugadorRepository;

@RestController
public class EquipoController {
	
	@Autowired
	private final JugadorRepository jugadorRepository;
	
	public EquipoController(JugadorRepository jR) {
		this.jugadorRepository = jR;
	}

	@GetMapping("/equipo/{id}")
	public List<Jugador> players(@PathVariable("id") int id){
		return jugadorRepository.getJugadores(id);
	}

}
