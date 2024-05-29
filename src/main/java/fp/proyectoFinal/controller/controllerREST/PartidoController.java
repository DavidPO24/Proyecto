package fp.proyectoFinal.controller.controllerREST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fp.proyectoFinal.model.Partido;
import fp.proyectoFinal.repository.EventoPartidoRepository;
import fp.proyectoFinal.repository.PartidoRepository;

@RestController
@RequestMapping("/partido")
public class PartidoController {
	
	@Autowired
	private final PartidoRepository partidoRepository;

	@Autowired
	private final EventoPartidoRepository eventoPartidoRepository;
	
	public PartidoController(PartidoRepository pR, EventoPartidoRepository epR) {
		this.partidoRepository = pR;
		this.eventoPartidoRepository = epR;
	}
	
	@GetMapping("/lista")
	public List<Partido> obtenerPartidos() {
		List<Partido> partidos = partidoRepository.findAll();
		for(Partido p: partidos) {
			p.setTieneDatos(eventoPartidoRepository.hayDatos(p.getIdpartido()) > 0);
		}
        return partidos;
    }

	@GetMapping("/{id}")
	public Partido obtenerPartido(@PathVariable("id") int id) {
		Partido p = partidoRepository.getPartido(id);
		p.setTieneDatos(eventoPartidoRepository.hayDatos(p.getIdpartido()) > 0);
        return p;
    }
}
