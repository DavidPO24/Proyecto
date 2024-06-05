package fp.proyectoFinal.model;

public class Clasificacion {
	
	private Equipo equipo;
	
	private int partidosJugados;
	
	private int victorias;
	
	private int empates;
	
	private int derrotas;
	
	private int puntos;
	
	private int golesFavor;
	
	private int golesContra;
	
	public Clasificacion() {
		this.partidosJugados = 0;
		this.victorias = 0;
		this.empates = 0;
		this.derrotas = 0;
		this.puntos = 0;
		this.golesFavor = 0;
		this.golesContra = 0;
	}

	public Clasificacion(Equipo equipo, int partidosJugados, int victorias, int empates, int derrotas, int puntos,
			int golesFavor, int golesContra) {
		this.equipo = equipo;
		this.partidosJugados = partidosJugados;
		this.victorias = victorias;
		this.empates = empates;
		this.derrotas = derrotas;
		this.puntos = puntos;
		this.golesFavor = golesFavor;
		this.golesContra = golesContra;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public int getPartidosJugados() {
		return partidosJugados;
	}

	public void setPartidosJugados(int partidosJugados) {
		this.partidosJugados = partidosJugados;
	}

	public int getVictorias() {
		return victorias;
	}

	public void setVictorias(int victorias) {
		this.victorias = victorias;
	}

	public int getEmpates() {
		return empates;
	}

	public void setEmpates(int empates) {
		this.empates = empates;
	}

	public int getDerrotas() {
		return derrotas;
	}

	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}

	public int getPuntos() {
		return puntos;
	}
	
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getGolesFavor() {
		return golesFavor;
	}

	public void setGolesFavor(int golesFavor) {
		this.golesFavor = golesFavor;
	}

	public int getGolesContra() {
		return golesContra;
	}

	public void setGolesContra(int golesContra) {
		this.golesContra = golesContra;
	}
	
	
}
