package fp.proyectoFinal.controller.controllerREST;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/session")
public class SessionController {

    @GetMapping("/set")
    public String setSession(HttpSession session) {
        session.setAttribute("username", "john_doe");
        return "Session attribute set";
    }

    @GetMapping("/get")
    public String getSession(HttpSession session) {
        return "Username: " + session.getAttribute("username");
    }
}