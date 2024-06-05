package fp.proyectoFinal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

import fp.proyectoFinal.controller.controllerREST.SessionController;
import jakarta.servlet.http.HttpSessionListener;

@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {

	private final SessionController sessionController;

	@Autowired
	public HttpSessionConfig(SessionController sessionController) {
		System.out.println("HttpSessionConfig initialized");
	    this.sessionController = sessionController;
	}
	    
    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setCookieName("SESSIONID");
        cookieSerializer.setCookiePath("/");
        cookieSerializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
        return cookieSerializer;
    }
    
    @Bean
    public HttpSessionListener httpSessionListener() {
    	//Llama a CustomSessionListener para reescribir lo que hace invalidate()
    	return new CustomSessionListener(sessionController); 
    }
}