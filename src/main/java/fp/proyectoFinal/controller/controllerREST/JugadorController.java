package fp.proyectoFinal.controller.controllerREST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
	
	@GetMapping("/jugador/modificar/{j}/{e}/{name}/{dorsal}")
	@Transactional
	public void modificarJugador(@PathVariable int j, @PathVariable int e, @PathVariable String name, @PathVariable int dorsal) {
		Jugador p = jugadorRepository.getReferenceById(j);
		p.setEquipo(equipoRepository.getReferenceById(e));
		p.setNombreJugador(name);
		p.setDorsal(dorsal);
		jugadorRepository.save(p);
	}
	
	@GetMapping("/jugador/lista/{id}")
	public List<Jugador> equipo(@PathVariable int id){
		return jugadorRepository.getJugadores(id);
	}
}
