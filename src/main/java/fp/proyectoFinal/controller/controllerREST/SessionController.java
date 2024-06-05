package fp.proyectoFinal.controller.controllerREST;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/session")
public class SessionController {

    @GetMapping("/set/{id}")
    public String setSession(HttpSession session, @PathVariable int id) {
    	session.removeAttribute("username");
        session.setAttribute("username", id);
        return "Session attribute set to: " + session.getAttribute("username");
    }

    @GetMapping("/get")
    public String getSession(HttpSession session) {
        return session.getAttribute("username") + "";
    }
    
    @GetMapping("/borrar")
    public String invalidateSession(HttpSession session) {
        session.invalidate();
        return "Sesion borrada";
    }
    
    @GetMapping("/update/{id}")
    public String updateSession(HttpSession session, @PathVariable int id) {
        session.setAttribute("username", id);
        return "Session attribute updated to: " + session.getAttribute("username");
    }
}