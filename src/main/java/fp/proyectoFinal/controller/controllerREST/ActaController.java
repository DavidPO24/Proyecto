package fp.proyectoFinal.controller.controllerREST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fp.proyectoFinal.model.EventoPartido;
import fp.proyectoFinal.model.EventosForm;
import fp.proyectoFinal.model.Jugador;
import fp.proyectoFinal.model.Partido;
import fp.proyectoFinal.model.Tipoevento;
import fp.proyectoFinal.repository.EventoPartidoRepository;
import fp.proyectoFinal.repository.JugadorRepository;
import fp.proyectoFinal.repository.PartidoRepository;
import fp.proyectoFinal.repository.TipoeventoRepository;

@RestController
public class ActaController {
	
	@Autowired
    private EventoPartidoRepository eventoPartidoRepository;

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private TipoeventoRepository tipoeventoRepository;

    @PostMapping("/acta/guardar/{id}")
    public void guardarActa(@PathVariable("id") int partidoId, @RequestBody List<EventosForm> eventos) {
        System.out.println("ESTAMOS EN GUARDARACTAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA \n");
        System.out.println("EL PARTIDOID tras la transformacion ES: " + partidoId);
        Partido partido = partidoRepository.getPartido(partidoId);
        System.out.println("EL NUMERO DE EVENTOS ES: " + eventos.size());
        EventoPartido ep;
        for (EventosForm evento : eventos) {
            Jugador j = jugadorRepository.getJugador(evento.getIdJugador());
            Tipoevento te = tipoeventoRepository.getTipoEvento(evento.getIdtipoEvento());
            ep = eventoPartidoRepository.save(new EventoPartido(j, partido, te, evento.getMomentoEvento()));
            System.out.println("EL EVENTO ES: " + ep.toString());
        }
    }

    @GetMapping("/acta/mostrar/{id}")
    public List<EventoPartido> getEventos(@PathVariable("id") int partidoId){
    	return eventoPartidoRepository.findByPartido(partidoId);
    }

}
