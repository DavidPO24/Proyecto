package fp.proyectoFinal.controller.controllerWEB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


import fp.proyectoFinal.model.ActaForm;
import fp.proyectoFinal.model.Entrenador;
import fp.proyectoFinal.model.Equipo;
import fp.proyectoFinal.model.EventoPartido;
import fp.proyectoFinal.model.EventosForm;
import fp.proyectoFinal.model.Jugador;
import fp.proyectoFinal.model.Partido;
import fp.proyectoFinal.model.Tipoevento;
import fp.proyectoFinal.model.Usuario;

@Controller
public class FutbolLigaControllerWEB{
	
	private final String USUARIOMANAGER_STRING = "http://localhost:8080/usuario/";
	private final String PARTIDOMANAGER_STRING = "http://localhost:8080/partido/";
	private final String EQUIPOMANAGER_STRING = "http://localhost:8080/equipo/";
	private final String ACTAMANAGER_STRING = "http://localhost:8080/acta/";
	private final String TIPOEVENTOMANAGER_STRING = "http://localhost:8080/tipoEvento/";
	private final String JUGADORMANAGER_STRING = "http://localhost:8080/jugador/";
	
	private RestTemplate restTemplate = new RestTemplate();
	
    // Devuelve la vista index.html
    @GetMapping("/")
    public String inicio() {
        return "index";     
    }
    
    @GetMapping("/registro")
	public String registro(Model model) {
    	List<Equipo> lista = new ArrayList<Equipo>();
        lista = Arrays.asList(restTemplate.getForEntity(USUARIOMANAGER_STRING + "registro", Equipo[].class).getBody());
        model.addAttribute("equipos", lista);
        return "registro";
    }
    
    @PostMapping("/sesion")
   	public String inicioSesion(Model model, @RequestParam("usuario") String usuario, @RequestParam("pwd") String pwd) {
    	String vuelta = "perfil";
       	Usuario u = new Usuario();
       	Jugador j = new Jugador();
           u = restTemplate.getForEntity(USUARIOMANAGER_STRING + "inicio/" + usuario + "/" + pwd, Usuario.class).getBody();
           if(u == null)
        	   vuelta = "redirect:/";
           else if(u.getTipousuario().getIdTipoUsuario() == 2) {
        	   j = restTemplate.getForEntity(USUARIOMANAGER_STRING + "jugador/" + u.getIdusuario(), Jugador.class).getBody();
        	   model.addAttribute("jugador", j);
           }
           return vuelta;
    }
    
    @PostMapping("/crear")
    public String crearCuenta(@RequestParam("usuario") String usuario, @RequestParam("pwd") String pwd, @RequestParam("nombre") String name,
    		@RequestParam("tipoUsuario") int idTipo, @RequestParam("equipo") int idEquipo, @RequestParam("dorsal") int dorsal) {
    	Usuario u = restTemplate.getForEntity(USUARIOMANAGER_STRING + "crear/" + usuario +
    					"/" + pwd + "/" + idTipo, Usuario.class).getBody();
    	if(u.getTipousuario().getNombreTipoUsuario().equals("jugador")) {
    		restTemplate.getForEntity(USUARIOMANAGER_STRING + "crear/" + dorsal + "/" +
    						idEquipo + "/" + u.getIdusuario() + "/" + name, Jugador.class).getBody();
    	}
    	else {
    		restTemplate.getForEntity(USUARIOMANAGER_STRING + "crear/" + idEquipo + "/" +
					 u + "/" + name, Entrenador.class).getBody();
    	}
    	return "redirect:/";
    }
    
    @GetMapping("/partidos")
    public String listarPartidos(Model model) {
        List<Partido> partidos = Arrays.asList(restTemplate.getForEntity(PARTIDOMANAGER_STRING + "lista", Partido[].class).getBody());
        model.addAttribute("partidos", partidos);
        return "partidos";
    }
    
    @PostMapping("/partidos/acta")
    public String actasArbitrales(@RequestParam("idPartido") int id, Model model) {
    	Partido p = restTemplate.getForEntity(PARTIDOMANAGER_STRING +
        		id, Partido.class).getBody();
    	List<Equipo> es = new ArrayList<Equipo>();
    	System.out.println("PARECE QUE EL PARTIDO CONTIENE: ");
    	System.out.println(p);
    	 model.addAttribute("partido", p);
    	if(p.getTieneDatos()) {
    		model.addAttribute("eventos", Arrays.asList(restTemplate.getForEntity(ACTAMANAGER_STRING 
    				+ "/mostrar/" + p.getIdpartido(), EventoPartido[].class).getBody()));
    		return "actaRellena";
    	}
    	es.add(p.getEquipoLocal());
    	es.add(p.getEquipoVisitante());
        model.addAttribute("eventos", new ArrayList<EventoPartido>());
        model.addAttribute("partido", p);
        model.addAttribute("equipos", es);
        model.addAttribute("jL", Arrays.asList(restTemplate.getForEntity(EQUIPOMANAGER_STRING 
        		+ p.getEquipoLocal().getIdEquipo(), Jugador[].class).getBody()));
        model.addAttribute("jV", Arrays.asList(restTemplate.getForEntity(EQUIPOMANAGER_STRING 
        		+ p.getEquipoVisitante().getIdEquipo(), Jugador[].class).getBody()));
        model.addAttribute("tiposEvento", Arrays.asList(restTemplate.getForEntity(TIPOEVENTOMANAGER_STRING + 
        		"eventos", Tipoevento[].class).getBody()));
        return "acta";
    }
    
    /*@PostMapping("/partidos/acta/guardar")
    public String guardarActa(@RequestParam("partidoId") int partidoId, @RequestParam("eventos") String eventosJson) {
    	restTemplate.getForEntity(ACTAMANAGER_STRING + partidoId + "/" + eventosJson, null);
    	return "redirect:/partido/lista";
    }*/

    @PostMapping("/partidos/acta/guardar")
    public String guardarActa(@RequestBody ActaForm eventos) {
        System.out.println("EL PARTIDOID ES: " + eventos.getPartidoId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<EventosForm>> requestEntity = new HttpEntity<>(eventos.getEventos(), headers);
        
        restTemplate.exchange(
            ACTAMANAGER_STRING + "/guardar/" + eventos.getPartidoId(),
            HttpMethod.POST,
            requestEntity,
            Void.class
        );

        return "redirect:/partidos";
    }

    @PostMapping("/perfil/cambiar")
    public String cambiarPerfil(@ModelAttribute("jugador") Jugador j, Model model) {
    	System.out.println(j);
    	//Jugador p = estTemplate.getForEntity(JUGADORMANAGER_STRING + "modificar/" + j.getIdjugador(), Jugador.class).getBody();
    	model.addAttribute("jugador", j);
    	return "perfil";
    }



}