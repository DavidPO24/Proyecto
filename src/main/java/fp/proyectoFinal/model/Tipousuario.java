package fp.proyectoFinal.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tipousuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "id_tipo_usuario")
    private Integer idTipoUsuario;
     
	@Column(name = "nombre_tipo_usuario") 
    private String nombreTipoUsuario;

    public Tipousuario() {
    }

	
    public Tipousuario(String nombreTipoUsuario) {
        this.nombreTipoUsuario = nombreTipoUsuario;
    }
   
    public Integer getIdTipoUsuario() {
        return this.idTipoUsuario;
    }
    
    public void setIdTipoUsuario(Integer idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }
    public String getNombreTipoUsuario() {
        return this.nombreTipoUsuario;
    }
    
    public void setNombreTipoUsuario(String nombreTipoUsuario) {
        this.nombreTipoUsuario = nombreTipoUsuario;
    }

}


