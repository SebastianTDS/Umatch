package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.TreeSet;

import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.dto.DatosDeMensaje;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.util.enums.Permiso;

public interface ServicioGrupo {

    Grupo crearGrupo(DatosDeGrupo grupoNuevo);

    List<Grupo> buscarTodos();

    List<Carrera> buscarTodasLasCarreras();

    List<Materia> buscarTodasLasMaterias();

    List<Grupo> buscarGrupoPorDatos(DatosDeGrupo datosParaBuscarUnGrupo);
    
    Grupo buscarGrupoPorID(Long idBuscado);

	void modificarGrupo(DatosDeGrupo formulario);

	void eliminarGrupo(Long idBuscado);

    void ingresarUsuarioAlGrupo(Long idUsuario, Long idGrupo);

    List<Grupo> buscarTodosMisGrupos(Usuario usuarioSesion);

	void validarPermiso(Long idUsuario, Long idGrupo, Permiso permisoAValidar);

    List<Grupo> buscarGruposDeMateria();

}
