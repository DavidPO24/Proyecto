package fp.proyectoFinal.controller.controllerREST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fp.proyectoFinal.model.Entrenador;
import fp.proyectoFinal.repository.EntrenadorRepository;

@RestController
public class EntrenadorController {

	@Autowired
	private final EntrenadorRepository entrenadorRepository;

	public EntrenadorController(EntrenadorRepository entrenadorRepository) {
		this.entrenadorRepository = entrenadorRepository;
	}
	
	@GetMapping("/entrenador/{id}")
	public Entrenador entrenadorEquipo(@PathVariable int id) {
		return entrenadorRepository.buscarPorEquipo(id);
	}
	
	@GetMapping("/entrenador/usuario/{id}")
	public Entrenador entrenadorUsuario(@PathVariable int id) {
		return entrenadorRepository.buscarPorUsuario(id);
	}
}
