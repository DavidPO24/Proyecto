package fp.proyectoFinal.controller.controllerREST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fp.proyectoFinal.model.Tipoevento;
import fp.proyectoFinal.repository.TipoeventoRepository;

@RestController
public class TipoeventoController{
	
	@Autowired
	private final TipoeventoRepository tipoeventoRepository;
	
	public TipoeventoController(TipoeventoRepository teR) {
		this.tipoeventoRepository = teR;
	}

	@GetMapping("/tipoEvento/eventos")
	public List<Tipoevento> eventos(){
		return tipoeventoRepository.findAll();
	}

}