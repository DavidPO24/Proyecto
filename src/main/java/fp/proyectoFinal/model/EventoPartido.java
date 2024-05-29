package fp.proyectoFinal.model;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class EventoPartido implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column (name = "idEventoPartido")
    private Integer ideventoPartido;
    
	@JoinColumn (name = "id_partido")
    @ManyToOne
    private Partido partido;
	
	@JoinColumn (name = "id_jugador")
    @ManyToOne
    private Jugador jugador;
	
	@JoinColumn (name = "id_tipo_evento")
    @ManyToOne
    private Tipoevento tipoevento;
    
    @Column(name = "momento_partido")
    private int momentoEvento;

    public EventoPartido() {
    }

    public EventoPartido(Jugador jugador, Partido partido, Tipoevento tipoevento, int momentoEvento) {
       this.jugador = jugador;
       this.partido = partido;
       this.tipoevento = tipoevento;
       this.momentoEvento = momentoEvento;
    }
   
    public Integer getIdeventoPartido() {
        return this.ideventoPartido;
    }
    
    public void setIdeventoPartido(Integer ideventoPartido) {
        this.ideventoPartido = ideventoPartido;
    }
    
    public Jugador getJugador() {
        return this.jugador;
    }
    
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    
    public Partido getPartido() {
        return this.partido;
    }
    
    public void setPartido(Partido partido) {
        this.partido = partido;
    }
    
    public Tipoevento getTipoevento() {
        return this.tipoevento;
    }
    
    public void setTipoevento(Tipoevento tipoevento) {
        this.tipoevento = tipoevento;
    }
    
    public int getMomentoEvento() {
        return this.momentoEvento;
    }
    
    public void setMomentoEvento(int momentoEvento) {
        this.momentoEvento = momentoEvento;
    }

	@Override
	public String toString() {
		return "EventoPartido [ideventoPartido=" + ideventoPartido + ", partido=" + partido + ", jugador=" + jugador
				+ ", tipoevento=" + tipoevento + ", momentoEvento=" + momentoEvento + "]";
	}

    
}


