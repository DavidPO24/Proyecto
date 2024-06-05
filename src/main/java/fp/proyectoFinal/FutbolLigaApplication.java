package fp.proyectoFinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
public class FutbolLigaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FutbolLigaApplication.class, args);
	}

}
