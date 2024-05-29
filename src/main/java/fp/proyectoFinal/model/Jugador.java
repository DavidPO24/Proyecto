package fp.proyectoFinal.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Jugador implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column (name = "id_jugador")
    private int idjugador;
    
    @Column(name = "nombre_jugador")
    private String nombreJugador;
    
    @Column(name = "dorsal")
    private int dorsal;
 
	@JoinColumn (name = "id_equipo")
    @ManyToOne
    private Equipo equipo;
    
	@OneToOne
	@JoinColumn (name = "id_usuario")
    private Usuario usuario;
	
    public Jugador() {
    }

	
    public Jugador(Equipo equipo, Usuario usuario, String nombreJugador, int dorsal) {
        this.equipo = equipo;
        this.usuario = usuario;
        this.nombreJugador = nombreJugador;
        this.dorsal = dorsal;
    }
   
    public Integer getIdjugador() {
        return this.idjugador;
    }
    
    public void setIdjugador(Integer idjugador) {
        this.idjugador = idjugador;
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
    public String getNombreJugador() {
        return this.nombreJugador;
    }
    
    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }
    public int getDorsal() {
        return this.dorsal;
    }
    
    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

}


