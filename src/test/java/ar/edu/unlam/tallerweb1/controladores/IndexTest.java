package ar.edu.unlam.tallerweb1.controladores;

import static ar.edu.unlam.tallerweb1.controladores.RegistroTest.servicio;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

public class IndexTest {

    @Test
    public void QueMePuedaMostrarLaPaginaDeLogin(){
        //dado
        ControladorLogin controladorLogin=new ControladorLogin();
        //cuando
         ModelAndView  mvc= controladorLogin.irALogin();
         //entonces
        Assert.assertEquals("login",(String) mvc.getViewName());
    }

    @Test
    public void QueMePuedaMostrarLaPaginaDeRegistro(){
        //dado
        ControladorRegistro controladorRegistro=new ControladorRegistro(servicio);
        //cuando
        ModelAndView  mvc= controladorRegistro.irARegistro();
        //entonces
        Assert.assertEquals("registro",(String) mvc.getViewName());
    }


}