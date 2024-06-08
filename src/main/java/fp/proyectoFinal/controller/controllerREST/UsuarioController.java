package fp.proyectoFinal.controller.controllerREST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fp.proyectoFinal.model.Entrenador;
import fp.proyectoFinal.model.Equipo;
import fp.proyectoFinal.model.Jugador;
import fp.proyectoFinal.model.Usuario;
import fp.proyectoFinal.repository.EntrenadorRepository;
import fp.proyectoFinal.repository.EquipoRepository;
import fp.proyectoFinal.repository.JugadorRepository;
import fp.proyectoFinal.repository.TipoUsuarioRepository;
import fp.proyectoFinal.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private final EquipoRepository equipoRepository;
	
	@Autowired
	private final UsuarioRepository usuarioRepository;
	
	@Autowired
	private final JugadorRepository jugadorRepository;
	
	@Autowired
	private final TipoUsuarioRepository tipoUsuarioRepository;
	
	@Autowired
	private final EntrenadorRepository entrenadorRepository;
	
	public UsuarioController(EquipoRepository eR, UsuarioRepository uR, JugadorRepository jR, TipoUsuarioRepository tuR, EntrenadorRepository entrenadorRepository) {
		this.equipoRepository = eR;
		this.usuarioRepository = uR;
		this.jugadorRepository = jR;
		this.tipoUsuarioRepository = tuR;
		this.entrenadorRepository = entrenadorRepository;
	}
	
	//En la llamada "/usuario/registro" vuelca un select de equipos dinamico
	@GetMapping("/registro")
	public List<Equipo> obtenerEquipos() {
        return equipoRepository.findAll();
    }
	
	@Transactional
	@RequestMapping("/inicio/{usuario}/{pwd}")
    public Usuario user(@PathVariable String usuario, @PathVariable String pwd){
        return usuarioRepository.iniciarSesion(usuario, pwd);
    }

	@Transactional
	@RequestMapping("/inicio/{id}")
    public Usuario usuario(@PathVariable int id){
        return usuarioRepository.getReferenceById(id);
    }
	
	@RequestMapping("/jugador/{id}")
	public Jugador player(@PathVariable int id) {
		return jugadorRepository.buscarPorUsuario(id);
	}
	
	@RequestMapping("/buscar/{nombre}")
	public Usuario user(@PathVariable String nombre) {
		return usuarioRepository.buscarPorNombre(nombre);
	}
	
	@Transactional
	@RequestMapping("/crear/{usuario}/{pwd}/{idTipo}")
    public Usuario crearUsuario(@PathVariable String usuario, @PathVariable String pwd, @PathVariable int idTipo){
        return usuarioRepository.save(new Usuario(tipoUsuarioRepository.getTipo(idTipo), usuario, pwd));
    }
	
	@Transactional
	@RequestMapping("/crear/{dorsal}/{idEquipo}/{u}/{name}")
	public void crearJugador(@PathVariable int dorsal, @PathVariable int idEquipo, @PathVariable int u, @PathVariable String name){
		jugadorRepository.save(new Jugador(equipoRepository.getReferenceById(idEquipo), usuarioRepository.getReferenceById(u), name, dorsal));
	}
	
	@Transactional
	@RequestMapping("/crearEntrenador/{idEquipo}/{u}/{name}")
	public void crearEntrenador(@PathVariable int idEquipo, @PathVariable int u, @PathVariable String name){
		entrenadorRepository.save(new Entrenador(equipoRepository.getReferenceById(idEquipo), usuarioRepository.getReferenceById(u), name));
	}
}
