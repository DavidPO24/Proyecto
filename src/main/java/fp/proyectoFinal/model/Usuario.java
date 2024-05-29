package fp.proyectoFinal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Usuario implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column( name = "idusuario")
	private int idusuario;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "password")
    private String password;

	@JoinColumn (name = "id_tipo_usuario")
    @ManyToOne
    private Tipousuario tipousuario;

    public Usuario() {
    }

	
    public Usuario(Tipousuario tipousuario, String nombre, String password) {
        this.tipousuario = tipousuario;
        this.nombre = nombre;
        this.password = password;
    }
    
    
    public Usuario(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

   
    public Integer getIdusuario() {
        return this.idusuario;
    }
    
    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }
    
    public Tipousuario getTipousuario() {
        return this.tipousuario;
    }
    
    public void setTipousuario(Tipousuario tipousuario) {
        this.tipousuario = tipousuario;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

}


