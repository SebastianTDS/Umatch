package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorHome {

    private final ServicioGrupo servicioGrupo;

    @Autowired
    public ControladorHome(ServicioGrupo servicioGrupo) {
        this.servicioGrupo=servicioGrupo;
    }


    @RequestMapping(value = "/ir-a-crear-nuevo-grupo")
    public ModelAndView irAlFormulario() {
        ModelMap model = new ModelMap();
        DatosDeGrupo datos= new DatosDeGrupo();
        model.put("datos",datos);
        return new ModelAndView("vistaParaCrearGrupo",model);
    }

    @RequestMapping("/ir-a-home")
    public ModelAndView irATest(){
        ModelMap model= new ModelMap();
        List<Grupo> grupos=servicioGrupo.buscarTodos();
        if(grupos.size()>0)
            model.put("grupos",grupos);
        else
            model.put("grupos","No nay grupos disponibles");
        return new ModelAndView("home",model);
    }



}
