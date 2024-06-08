package fp.proyectoFinal.model;

import java.util.Date;


import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Partido  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column (name = "idPartido")
	private Integer idpartido;
	
	@Column(name = "jornada")
    private int jornada;
    
	@JoinColumn (name = "equipo_local")
    @ManyToOne(fetch = FetchType.EAGER)
    private Equipo equipoLocal;
    
	@JoinColumn (name = "equipo_visitante")
    @ManyToOne(fetch = FetchType.EAGER)
    private Equipo equipoVisitante;
    
	@Column(name = "fecha_partido")
    private Date fechaPartido;
    
	@Column(name = "estadio")
    private String estadio;

	private boolean tieneDatos = false;
	
    public Partido() {
    }

	
    public Partido(int jornada, Equipo equipoLocal, Equipo equipoVisitante, Date date, String estadio) {
		super();
		this.jornada = jornada;
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
		this.fechaPartido = date;
		this.estadio = estadio;
	}




	@Override
	public String toString() {
		return "Partido [idpartido=" + idpartido + ", jornada=" + jornada + ", equipoLocal=" + equipoLocal
				+ ", equipoVisitante=" + equipoVisitante + ", fechaPartido=" + fechaPartido + ", estadio=" + estadio
				+ ", tieneDatos=" + tieneDatos + "]";
	}


	public Partido(Equipo equipoByEquipoVisitante, Equipo equipoByEquipoLocal, int jornada, Date fechaPartido, String estadio) {
        this.equipoLocal = equipoByEquipoVisitante;
        this.equipoVisitante = equipoByEquipoLocal;
        this.jornada = jornada;
        this.fechaPartido = fechaPartido;
        this.estadio = estadio;
    }
   
    public Integer getIdpartido() {
        return this.idpartido;
    }
    
    public void setIdpartido(Integer idpartido) {
        this.idpartido = idpartido;
    }
    
    public Equipo getEquipoLocal() {
        return this.equipoLocal;
    }
    
    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }
    
    public Equipo getEquipoVisitante() {
        return this.equipoVisitante;
    }
    
    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }
    
    public int getJornada() {
        return this.jornada;
    }
    
    public void setJornada(int jornada) {
        this.jornada = jornada;
    }
    
    public Date getFechaPartido() {
        return this.fechaPartido;
    }
    
    public void setFechaPartido(Date fechaPartido) {
        this.fechaPartido = fechaPartido;
    }
    
    public String getEstadio() {
        return this.estadio;
    }
    
    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }


	public boolean getTieneDatos() {
		return tieneDatos;
	}

	public void setTieneDatos(boolean tieneDatos) {
		this.tieneDatos = tieneDatos;
	}

}


