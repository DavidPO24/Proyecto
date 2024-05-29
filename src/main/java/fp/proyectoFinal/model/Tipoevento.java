package fp.proyectoFinal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tipoevento  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "idtipo_evento")
	private Integer idtipoEvento;
    
	@Column(name = "nombre_evento") 
    private String nombreEvento;

    public Tipoevento() {
    }
	
    public Tipoevento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }
   
    public Integer getIdtipoEvento() {
        return this.idtipoEvento;
    }
    
    public void setIdtipoEvento(Integer idtipoEvento) {
        this.idtipoEvento = idtipoEvento;
    }
    public String getNombreEvento() {
        return this.nombreEvento;
    }
    
    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

}


