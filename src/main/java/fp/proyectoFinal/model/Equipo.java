package fp.proyectoFinal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Equipo  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "idEquipo")
	private int idEquipo;
    
	@Column(name = "nombre_equipo")
    private String nombreEquipo;

    public Equipo() {}

    public Equipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }
   
    public int getIdEquipo() {
        return this.idEquipo;
    }
    
    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }
    
    public String getNombreEquipo() {
        return this.nombreEquipo;
    }
    
    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

}


