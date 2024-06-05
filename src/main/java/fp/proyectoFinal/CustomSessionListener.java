package fp.proyectoFinal;

import fp.proyectoFinal.controller.controllerREST.SessionController;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class CustomSessionListener implements HttpSessionListener {

	private final SessionController sessionController;

    public CustomSessionListener(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    	//Reescribe la función invalidate() de session
        sessionController.invalidateSession(se.getSession());
    }
    
}
