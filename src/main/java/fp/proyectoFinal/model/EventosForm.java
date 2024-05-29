package fp.proyectoFinal.model;

public class EventosForm {
	
    private int idJugador;
    
    private int idtipoEvento;
    
    private int momentoEvento;

	public EventosForm(int idJugador, int idtipoEvento, int momentoEvento) {
		this.idJugador = idJugador;
		this.idtipoEvento = idtipoEvento;
		this.momentoEvento = momentoEvento;
	}

	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public int getIdtipoEvento() {
		return idtipoEvento;
	}

	public void setIdtipoEvento(int idtipoEvento) {
		this.idtipoEvento = idtipoEvento;
	}

	public int getMomentoEvento() {
		return momentoEvento;
	}

	public void setMomentoEvento(int momentoEvento) {
		this.momentoEvento = momentoEvento;
	}


}
