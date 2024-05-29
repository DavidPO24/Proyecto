package fp.proyectoFinal.controller.controllerREST;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fp.proyectoFinal.repository.EquipoRepository;
import fp.proyectoFinal.repository.JugadorRepository;
import fp.proyectoFinal.repository.UsuarioRepository;
import fp.proyectoFinal.model.Clasificacion;
import fp.proyectoFinal.model.Equipo;

@RestController
@RequestMapping("/clasificacion")
public class ClasificacionController {

	@Autowired
	private final EquipoRepository equipoRepository;
	
	@Autowired
	private final UsuarioRepository usuarioRepository;
	
	@Autowired
	private final JugadorRepository jugadorRepository;
	
	public ClasificacionController(EquipoRepository eR, UsuarioRepository uR, JugadorRepository jR) {
		this.equipoRepository = eR;
		this.usuarioRepository = uR;
		this.jugadorRepository = jR;
	}
	
	public List<Clasificacion> clasificacion(){
		List <Clasificacion> lista = new ArrayList<Clasificacion>();
		List <Equipo> equipos = equipoRepository.findAll();
		Clasificacion team;
		for(Equipo e: equipos) {
			team = new Clasificacion();
			team.setEquipo(e);
			
		}
		return lista;
	}
}
