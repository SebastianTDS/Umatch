package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupos;

@Controller
@RequestMapping("/grupos")
public class ControladorGrupos {

	private ServicioGrupos service;

	@Autowired
	public ControladorGrupos(ServicioGrupos service) {
		this.service = service;
	}

	@RequestMapping("/{id}")
	public ModelAndView buscarGrupo(@PathVariable Long id) {
		ModelMap modelo = new ModelMap();
		Grupo buscado = service.buscarGrupoPorID(id);

		if (buscado != null) {
			modelo.put("formulario", new Grupo());
			modelo.put("grupo", buscado);
			return new ModelAndView("vistaGrupo", modelo);
		} else
			return new ModelAndView("redirect:/");
	}

	@RequestMapping(path = "/{id}/modificarGrupo", method = RequestMethod.POST)
	public ModelAndView cambiarDatosGrupo(@PathVariable Long id, @ModelAttribute("formulario") Grupo form) {
		ModelMap modelo = new ModelMap();

		service.modificarGrupo(id, form);
		modelo.put("mensaje", "Datos actualizados");

		return new ModelAndView("redirect:/grupos/" + id, modelo);
	}

	@RequestMapping(path = "/eliminarGrupo")
	public ModelAndView eliminarGrupo(@RequestParam(required = false) Long id) {
		ModelMap modelo = new ModelMap();

		if (id == null) return new ModelAndView("redirect:/");
		
		service.eliminarGrupo(id);
		modelo.put("mensaje", "Grupo eliminado con exito!");
		return new ModelAndView("redirect:/", modelo);
	}

}
