package fp.proyectoFinal.service;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fp.proyectoFinal.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class UsuarioService {
	
	@PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Usuario getUsuarioConAsociacionesInicializadas(int id) {
        Usuario u = entityManager.find(Usuario.class, id);
        initializeLazyAssociations(u);
        return u;
    }

	public void initializeLazyAssociations(Usuario usuario) {
	    if (usuario != null) {
	        Hibernate.initialize(usuario.getTipousuario());
	        // Agrega aquí todas las asociaciones perezosas que necesites inicializar
	    }
	}
}
