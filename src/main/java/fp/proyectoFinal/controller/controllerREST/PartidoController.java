package fp.proyectoFinal.controller.controllerREST;

import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fp.proyectoFinal.model.Partido;
import fp.proyectoFinal.repository.EquipoRepository;
import fp.proyectoFinal.repository.EventoPartidoRepository;
import fp.proyectoFinal.repository.PartidoRepository;

@RestController
@RequestMapping("/partido")
public class PartidoController {
	
	@Autowired
	private final PartidoRepository partidoRepository;

	@Autowired
	private final EventoPartidoRepository eventoPartidoRepository;
	
	@Autowired
	private final EquipoRepository equipoRepository;
	
	public PartidoController(PartidoRepository pR, EventoPartidoRepository epR, EquipoRepository equipoRepository) {
		this.partidoRepository = pR;
		this.eventoPartidoRepository = epR;
		this.equipoRepository = equipoRepository;
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
	
	@GetMapping("/jugados/{id}")
	public List<Integer> datosClasificacion(@PathVariable("id") int id) {
        List<Partido> partidos = partidoRepository.getLocal(id);
        partidos.addAll(partidoRepository.getVisitantes(id));
        
        int[] jugados = new int[5]; // [ganados, empatados, perdidos, golesFavor, golesContra]

        partidos.stream()
                .filter(p -> eventoPartidoRepository.hayDatos(p.getIdpartido()) > 0)
                .forEach(p -> {
                    int golesLocal = eventoPartidoRepository.goles(p.getIdpartido(), p.getEquipoLocal().getIdEquipo());
                    int golesVisitante = eventoPartidoRepository.goles(p.getIdpartido(), p.getEquipoVisitante().getIdEquipo());
                    
                    if (p.getEquipoLocal().getIdEquipo() == id && golesLocal > golesVisitante) {
                        jugados[0]++; // Ganados
                        jugados[3] += golesLocal;
                        jugados[4] += golesVisitante;
                    } 
                    else if((p.getEquipoVisitante().getIdEquipo() == id && golesLocal < golesVisitante)) {
                    	jugados[0]++;
                    	jugados[4] += golesLocal;
                        jugados[3] += golesVisitante;
                    }
                    else if (golesLocal == golesVisitante) {
                        jugados[1]++; // Empatados
                        jugados[3] += golesLocal;
                        jugados[4] += golesVisitante;
                    } else {
                        jugados[2]++; // Perdidos
                        if(p.getEquipoLocal().getIdEquipo() == id) {
                        	jugados[3] += golesLocal;
                            jugados[4] += golesVisitante;
                        }  	
                        else {
                        	jugados[4] += golesLocal;
                            jugados[3] += golesVisitante;
                        }
                        	
                    }
                });

        return Arrays.asList(jugados[0], jugados[1], jugados[2], jugados[3], jugados[4]);
	}
	
	@GetMapping("/crear/{idEquipoLocal}/{idEquipoVisitante}/{fecha}/{estadio}/{j}")
	public void crearPartido(@PathVariable("idEquipoLocal") int idL, @PathVariable("idEquipoVisitante") int idV, @PathVariable("fecha") String fecha,
			@PathVariable("estadio") String estadio, @PathVariable("j") int j) {
		
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        LocalDateTime localDateTime = LocalDateTime.parse(fecha, format);

        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        
		partidoRepository.save(new Partido(j, equipoRepository.getReferenceById(idL), equipoRepository.getReferenceById(idV), date, estadio));
	}
}
