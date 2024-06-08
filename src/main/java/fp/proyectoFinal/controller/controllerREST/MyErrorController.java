package fp.proyectoFinal.controller.controllerREST;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {


    public String getErrorPath() {
        return "/error";
    }
}
