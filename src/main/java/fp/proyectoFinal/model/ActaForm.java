package fp.proyectoFinal.model;

import java.util.List;

public class ActaForm {
	
	private int partidoId;
	
	private List<EventosForm> eventos;

	public int getPartidoId() {
		return partidoId;
	}

	public void setPartidoId(int partidoId) {
		this.partidoId = partidoId;
	}

	public List<EventosForm> getEventos() {
		return eventos;
	}

	public void setEventos(List<EventosForm> eventos) {
		this.eventos = eventos;
	}

	
}
