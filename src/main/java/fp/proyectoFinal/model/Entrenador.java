package fp.proyectoFinal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Entrenador implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column (name = "idEntrenador")
	private Integer identrenador;
	
	@Column (name = "nombre")
    private String nombre;
	
	@OneToOne
	@JoinColumn (name = "id_equipo")
    private Equipo equipo;
	
	@OneToOne
	@JoinColumn (name = "id_usuario")
    private Usuario usuario;

    public Entrenador() {
    }

    public Entrenador(Equipo equipo, Usuario usuario, String nombre) {
       this.equipo = equipo;
       this.usuario = usuario;
       this.nombre = nombre;
    }
   
    public Integer getIdentrenador() {
        return this.identrenador;
    }
    
    public void setIdentrenador(Integer identrenador) {
        this.identrenador = identrenador;
    }
    public Equipo getEquipo() {
        return this.equipo;
    }
    
    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}


