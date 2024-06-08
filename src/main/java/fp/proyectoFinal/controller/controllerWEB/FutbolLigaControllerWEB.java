package fp.proyectoFinal.controller.controllerWEB;

import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


import fp.proyectoFinal.model.ActaForm;
import fp.proyectoFinal.model.Clasificacion;
import fp.proyectoFinal.model.Entrenador;
import fp.proyectoFinal.model.Equipo;
import fp.proyectoFinal.model.EventoPartido;
import fp.proyectoFinal.model.EventosForm;
import fp.proyectoFinal.model.Jugador;
import fp.proyectoFinal.model.Partido;
import fp.proyectoFinal.model.Tipoevento;
import fp.proyectoFinal.model.Usuario;
import fp.proyectoFinal.service.UsuarioService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class FutbolLigaControllerWEB{
	
	@Autowired
    private UsuarioService usuarioService;
	
	private final String USUARIOMANAGER_STRING = "http://localhost:8080/usuario/";
	private final String PARTIDOMANAGER_STRING = "http://localhost:8080/partido/";
	private final String EQUIPOMANAGER_STRING = "http://localhost:8080/equipo/";
	private final String ACTAMANAGER_STRING = "http://localhost:8080/acta/";
	private final String TIPOEVENTOMANAGER_STRING = "http://localhost:8080/tipoEvento/";
	private final String JUGADORMANAGER_STRING = "http://localhost:8080/jugador/";
	private final String SESSIONMANAGER_STRING = "http://localhost:8080/session/";
	private final String ENTRENADORMANAGER_STRING = "http://localhost:8080/entrenador/";
	
	private RestTemplate restTemplate = new RestTemplate();
	private HttpHeaders headers = new HttpHeaders();
	
    // Devuelve la vista index.html
    @GetMapping("/")
    public String inicio() {
        return "index";     
    }
    
 // Devuelve la vista registro.html
    @GetMapping("/registro")
	public String registro(Model model) {
    	List<Equipo> lista = new ArrayList<Equipo>();
        lista = Arrays.asList(restTemplate.getForEntity(USUARIOMANAGER_STRING + "registro", Equipo[].class).getBody());
        model.addAttribute("equipos", lista);
        return "registro";
    }
    
    // Codigo de comprobación de sesión
    @PostMapping("/sesion")
   	public String inicioSesion(Model model, @RequestParam("usuario") String usuario, @RequestParam("pwd") String pwd) {
    	Usuario u = restTemplate.getForEntity(USUARIOMANAGER_STRING + "inicio/" + usuario + "/" + pwd, Usuario.class).getBody();
		Jugador j = new Jugador();
    	if (u == null) {
           model.addAttribute("error", "Usuario o contraseña incorrectos");
     	   return "index";
        }

           ResponseEntity<String> response = restTemplate.getForEntity(
                   SESSIONMANAGER_STRING + "set/" + u.getIdusuario(), String.class);

           // Extraer cookies de la respuesta
           List<String> cookies = response.getHeaders().get(HttpHeaders.SET_COOKIE);
           if (cookies != null) {
               for (String cookie : cookies) {
                   headers.add(HttpHeaders.COOKIE, cookie);
               }
           }
           System.out.println(response.getBody());
           model.addAttribute("usuario", u);
           if(u.getTipousuario().getIdTipoUsuario() == 2) {
        	   j = restTemplate.getForEntity(USUARIOMANAGER_STRING + "jugador/" + u.getIdusuario(), Jugador.class).getBody();
        	   model.addAttribute("jugador", j);
        	   model.addAttribute("equipo", j.getEquipo());
        	   model.addAttribute("goles", restTemplate.getForEntity(ACTAMANAGER_STRING 
       				+ "/datos/" + j.getIdjugador() + "/" + 1, Integer.class).getBody());
               model.addAttribute("tarjetasAmarillas", restTemplate.getForEntity(ACTAMANAGER_STRING 
       				+ "/datos/" + j.getIdjugador() + "/" + 2, Integer.class).getBody());
               model.addAttribute("tarjetasRojas", restTemplate.getForEntity(ACTAMANAGER_STRING 
       				+ "/datos/" + j.getIdjugador() + "/" + 3, Integer.class).getBody());
           }
           else if(u.getTipousuario().getIdTipoUsuario() == 3) {
        	   return "redirect:/equipo";
           }   
           else
        	   return "redirect:/partidos";
           
           return "perfil";
    }
    
 // Devuelve la vista principal de cada tipo de usuario, si no está iniciada una sesión devuelve a index
    @Transactional
    @GetMapping("/sesion")
    public String perfil(Model model, HttpServletRequest request) {
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                SESSIONMANAGER_STRING + "get", HttpMethod.GET, requestEntity, String.class);

        String responseBody = response.getBody();
        System.out.println(responseBody);

        if (responseBody == null || responseBody.equals("null")) {
        	restTemplate.exchange(SESSIONMANAGER_STRING + "borrar", HttpMethod.GET, requestEntity, String.class);
            return "redirect:/";
        }

        int id = Integer.parseInt(responseBody);
        Usuario u = usuarioService.getUsuarioConAsociacionesInicializadas(id);

        model.addAttribute("usuario", u);
        restTemplate.exchange(SESSIONMANAGER_STRING + "update/" + u.getIdusuario(), HttpMethod.GET, requestEntity, String.class);
        if (u.getTipousuario().getIdTipoUsuario() == 2) {
            Jugador j = restTemplate.getForEntity(USUARIOMANAGER_STRING + "jugador/" + u.getIdusuario(), Jugador.class).getBody();
            model.addAttribute("jugador", j);
            model.addAttribute("equipo", j.getEquipo());
            model.addAttribute("goles", restTemplate.getForEntity(ACTAMANAGER_STRING 
    				+ "/datos/" + j.getIdjugador() + "/" + 1, Integer.class).getBody());
            model.addAttribute("tarjetasAmarillas", restTemplate.getForEntity(ACTAMANAGER_STRING 
    				+ "/datos/" + j.getIdjugador() + "/" + 2, Integer.class).getBody());
            model.addAttribute("tarjetasRojas", restTemplate.getForEntity(ACTAMANAGER_STRING 
    				+ "/datos/" + j.getIdjugador() + "/" + 3, Integer.class).getBody());
        }
        else if(u.getTipousuario().getIdTipoUsuario() == 3) {
     	   return "redirect:/equipo";
        }   
        else
     	   return "redirect:/partidos";
        
        return "perfil";
    }

    //Codigo para crear la cuenta
    @Transactional
    @PostMapping("/crear")
    public String crearCuenta(Model model, @RequestParam("usuario") String usuario, @RequestParam("pwd") String pwd, @RequestParam("nombre") String name,
    		@RequestParam("tipoUsuario") int idTipo, @RequestParam("equipo") int idEquipo, @RequestParam("dorsal") int dorsal) {
    	Usuario e = restTemplate.getForEntity(USUARIOMANAGER_STRING + "buscar/" + usuario, Usuario.class).getBody();
    	if(e != null) {
    		model.addAttribute("error", "Ya existe usuario con ese nombre");
      	   	return "registro";
    	}
    	Usuario u = restTemplate.getForEntity(USUARIOMANAGER_STRING + "crear/" + usuario +
    					"/" + pwd + "/" + idTipo, Usuario.class).getBody();
    	u = usuarioService.getUsuarioConAsociacionesInicializadas(u.getIdusuario());
    	if(u.getTipousuario().getNombreTipoUsuario().equals("jugador")) {
    		restTemplate.getForEntity(USUARIOMANAGER_STRING + "crear/" + dorsal + "/" +
    						idEquipo + "/" + u.getIdusuario() + "/" + name, Jugador.class).getBody();
    	}
    	else {
    		restTemplate.getForEntity(USUARIOMANAGER_STRING + "crearEntrenador/" + idEquipo + "/" +
					 u.getIdusuario() + "/" + name, Entrenador.class).getBody();
    	}
    	return "redirect:/";
    }
    
    //Pagina html partidos
    @GetMapping("/partidos")
    public String listarPartidos(Model model) {
    	 HttpEntity<String> requestEntity = new HttpEntity<>(headers);

         ResponseEntity<String> response = restTemplate.exchange(
                 SESSIONMANAGER_STRING + "get", HttpMethod.GET, requestEntity, String.class);

         String responseBody = response.getBody();
         System.out.println(responseBody);

         if (responseBody == null || responseBody.equals("null")) {
         	restTemplate.exchange(SESSIONMANAGER_STRING + "borrar", HttpMethod.GET, requestEntity, String.class);
             return "redirect:/";
         }

         int id = Integer.parseInt(responseBody);
         Usuario u = usuarioService.getUsuarioConAsociacionesInicializadas(id);

         model.addAttribute("usuario", u);
        List<Partido> partidos = Arrays.asList(restTemplate.getForEntity(PARTIDOMANAGER_STRING + "lista", Partido[].class).getBody());
        model.addAttribute("partidos", partidos);
        return "partidos";
    }
    
    //Rellena los datos necesarios para hacer funcionar la pagina acta.html
    @PostMapping("/partidos/acta")
    public String actasArbitrales(@RequestParam("idPartido") int id, Model model) {
    	Partido p = restTemplate.getForEntity(PARTIDOMANAGER_STRING +
        		id, Partido.class).getBody();
    	List<Equipo> es = new ArrayList<Equipo>();
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

    //Guarda el acta arbitral en BBDD
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

    //Cambia los datos del perfil
    @Transactional
    @PostMapping("/perfil/cambiar")
    public String cambiarPerfil(@RequestParam("idJugador") int j, @RequestParam("equipoId") int e , @RequestParam("nombreJugador") String name, 
    		@RequestParam("dorsal") int dorsal, Model model) {
    	restTemplate.getForEntity(JUGADORMANAGER_STRING + "modificar/" + j + "/" + e + "/"
    	+ name + "/" + dorsal, Jugador.class).getBody();
    	return "redirect:/sesion";
    }

    //Enseña la tabla de clasificación
    @GetMapping("/clasificacion")
    public String getClasificacion(Model model) {
    	 HttpEntity<String> requestEntity = new HttpEntity<>(headers);

         ResponseEntity<String> response = restTemplate.exchange(
                 SESSIONMANAGER_STRING + "get", HttpMethod.GET, requestEntity, String.class);

         String responseBody = response.getBody();
         System.out.println(responseBody);

         if (responseBody == null || responseBody.equals("null")) {
         	restTemplate.exchange(SESSIONMANAGER_STRING + "borrar", HttpMethod.GET, requestEntity, String.class);
             return "redirect:/";
         }

         int id = Integer.parseInt(responseBody);
         Usuario u = usuarioService.getUsuarioConAsociacionesInicializadas(id);
         model.addAttribute("usuario", u);
    	List<Equipo> lista = new ArrayList<Equipo>();
        lista = Arrays.asList(restTemplate.getForEntity(USUARIOMANAGER_STRING + "registro", Equipo[].class).getBody());
        List<Clasificacion> clasificaciones = new ArrayList<Clasificacion>();
       for(Equipo e: lista) {
        	List<Integer> resultados = Arrays.asList(restTemplate.getForEntity(PARTIDOMANAGER_STRING + "jugados/" + e.getIdEquipo(), Integer[].class).getBody());
        	clasificaciones.add(new Clasificacion(e, 
        			resultados.get(0) + resultados.get(1) + resultados.get(2),
        				resultados.get(0),
        				resultados.get(1),
        				resultados.get(2),
        				3 * resultados.get(0) + resultados.get(1),
        				resultados.get(3),
        				resultados.get(4)
        			));
        }
        //Criterios de ordenación
        List<Clasificacion> clasificacionesOrdenadas = clasificaciones.stream()
                .sorted(Comparator.comparingInt(Clasificacion::getPuntos).reversed()
                        .thenComparingInt(c -> c.getGolesFavor() - c.getGolesContra()).reversed()
                        .thenComparingInt(Clasificacion::getGolesFavor).reversed())
                .collect(Collectors.toList());
        model.addAttribute("clasificaciones", clasificacionesOrdenadas);
        return "clasificacion";
    }
    
    //Borra la sesión almacenada y vuelve a index.html
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (headers != null && headers.get(HttpHeaders.COOKIE) != null) {
            httpHeaders.put(HttpHeaders.COOKIE, headers.get(HttpHeaders.COOKIE));
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        restTemplate.exchange(SESSIONMANAGER_STRING + "borrar", HttpMethod.GET, requestEntity, String.class);

        // Limpiar cookies de cliente
        for (String cookie : headers.get(HttpHeaders.COOKIE)) {
            Cookie clearedCookie = new Cookie(cookie.split("=")[0], null);
            clearedCookie.setPath("/");
            clearedCookie.setMaxAge(0);
            response.addCookie(clearedCookie);
        }
        return "redirect:/";
    }

    //Pagina equipo, cargando los datos desde BBDD
    @GetMapping("/equipo")
    public String equipo(Model model) {
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                SESSIONMANAGER_STRING + "get", HttpMethod.GET, requestEntity, String.class);

        String responseBody = response.getBody();
        System.out.println(responseBody);

        if (responseBody == null || responseBody.equals("null")) {
        	restTemplate.exchange(SESSIONMANAGER_STRING + "borrar", HttpMethod.GET, requestEntity, String.class);
            return "redirect:/";
        }

        int id = Integer.parseInt(responseBody);
        Usuario u = usuarioService.getUsuarioConAsociacionesInicializadas(id);
        Jugador j;
        Entrenador e;
        if(u.getTipousuario().getIdTipoUsuario() == 2) {
        	 j = restTemplate.getForEntity(USUARIOMANAGER_STRING + "jugador/" + u.getIdusuario(), Jugador.class).getBody();
             e = restTemplate.getForEntity(ENTRENADORMANAGER_STRING + j.getEquipo().getIdEquipo(), Entrenador.class).getBody();
             model.addAttribute("equipo", j.getEquipo());
             model.addAttribute("jugadores", restTemplate.getForEntity(JUGADORMANAGER_STRING + "lista/" + j.getEquipo().getIdEquipo(), Jugador[].class).getBody());
        }
        else {
        	e = restTemplate.getForEntity(ENTRENADORMANAGER_STRING + "usuario/" +  u.getIdusuario(), Entrenador.class).getBody();
        	model.addAttribute("jugadores", restTemplate.getForEntity(JUGADORMANAGER_STRING + "lista/" + e.getEquipo().getIdEquipo(), Jugador[].class).getBody());
        	model.addAttribute("equipo", e.getEquipo());
        }
        model.addAttribute("usuario", u);
        restTemplate.exchange(SESSIONMANAGER_STRING + "update/" + u.getIdusuario(), HttpMethod.GET, requestEntity, String.class);
        model.addAttribute("entrenador", e);
    	return "equipo";
    }
    
    @PostMapping("/crear/equipo")
    public String crearEquipo(@RequestParam("nombre") String nombre) {
    	restTemplate.getForEntity(EQUIPOMANAGER_STRING + "crear/" + nombre, Equipo.class).getBody();
    	return "redirect:/admin/equipo";
    }
    
    @GetMapping("/admin/equipo")
    public String nuevoEquipo(Model model) {
    	 HttpEntity<String> requestEntity = new HttpEntity<>(headers);

         ResponseEntity<String> response = restTemplate.exchange(
                 SESSIONMANAGER_STRING + "get", HttpMethod.GET, requestEntity, String.class);

         String responseBody = response.getBody();
         System.out.println(responseBody);

         if (responseBody == null || responseBody.equals("null")) {
         	restTemplate.exchange(SESSIONMANAGER_STRING + "borrar", HttpMethod.GET, requestEntity, String.class);
             return "redirect:/";
         }

         int id = Integer.parseInt(responseBody);
         Usuario u = usuarioService.getUsuarioConAsociacionesInicializadas(id);
         model.addAttribute("usuario", u);
    	return "crearEquipo";
    }
    
    @GetMapping("/admin/partido")
    public String nuevoPartido(Model model) {
    	 HttpEntity<String> requestEntity = new HttpEntity<>(headers);

         ResponseEntity<String> response = restTemplate.exchange(
                 SESSIONMANAGER_STRING + "get", HttpMethod.GET, requestEntity, String.class);

         String responseBody = response.getBody();
         System.out.println(responseBody);

         if (responseBody == null || responseBody.equals("null")) {
         	restTemplate.exchange(SESSIONMANAGER_STRING + "borrar", HttpMethod.GET, requestEntity, String.class);
             return "redirect:/";
         }

         int id = Integer.parseInt(responseBody);
         Usuario u = usuarioService.getUsuarioConAsociacionesInicializadas(id);
         model.addAttribute("usuario", u);
         List<Equipo> lista = Arrays.asList(restTemplate.getForEntity(USUARIOMANAGER_STRING + "registro", Equipo[].class).getBody());
         model.addAttribute("equipos", lista);
    	return "crearPartido";
    }
    
    @PostMapping("/crear/partido")
    public String crearPartido(@RequestParam("jornada") int j, @RequestParam("estadio") String estadio, @RequestParam("fechaPartido") String fecha, @RequestParam("equipoLocal") int idEquipoLocal, 
    		@RequestParam("equipoVisitante") int idEquipoVisitante) {
    	restTemplate.getForEntity(PARTIDOMANAGER_STRING + "crear/" + idEquipoLocal + "/" + idEquipoVisitante + "/" + fecha +
				"/" + estadio + "/" + j, Partido.class).getBody();
    	return "redirect:/admin/equipo";
    }
    
    @GetMapping("/admin/arbitro")
    public String nuevoArbitro(Model model) {
    	HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                SESSIONMANAGER_STRING + "get", HttpMethod.GET, requestEntity, String.class);

        String responseBody = response.getBody();
        System.out.println(responseBody);

        if (responseBody == null || responseBody.equals("null")) {
        	restTemplate.exchange(SESSIONMANAGER_STRING + "borrar", HttpMethod.GET, requestEntity, String.class);
            return "redirect:/";
        }

        int id = Integer.parseInt(responseBody);
        Usuario u = usuarioService.getUsuarioConAsociacionesInicializadas(id);
        model.addAttribute("usuario", u);
    	return "registroArbitros";
    }
    @PostMapping("/crear/arbitro")
    public String crearArbitro(@RequestParam("usuario") String usuario, @RequestParam("pwd") String pwd) {
    	Usuario u = restTemplate.getForEntity(USUARIOMANAGER_STRING + "crear/" + usuario +
				"/" + pwd + "/" + 4, Usuario.class).getBody();
    	u = usuarioService.getUsuarioConAsociacionesInicializadas(u.getIdusuario());
    	return "redirect:/admin/equipo";
    }
    
    //Manejo personalizado de errores en la aplicación, en este caso te echa de tu sesión y devuelve a index.html
    @RequestMapping("/error")
    public String error(HttpServletRequest request, HttpServletResponse response) {
    	 HttpHeaders httpHeaders = new HttpHeaders();
         if (headers != null && headers.get(HttpHeaders.COOKIE) != null) {
             httpHeaders.put(HttpHeaders.COOKIE, headers.get(HttpHeaders.COOKIE));
         }
         HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

         restTemplate.exchange(SESSIONMANAGER_STRING + "borrar", HttpMethod.GET, requestEntity, String.class);

         // Limpiar cookies de cliente
         for (String cookie : headers.get(HttpHeaders.COOKIE)) {
             Cookie clearedCookie = new Cookie(cookie.split("=")[0], null);
             clearedCookie.setPath("/");
             clearedCookie.setMaxAge(0);
             response.addCookie(clearedCookie);
         }
    	return "redirect:/";
    }
}