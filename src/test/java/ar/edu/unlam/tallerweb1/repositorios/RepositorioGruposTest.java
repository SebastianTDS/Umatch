package ar.edu.unlam.tallerweb1.repositorios;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Carrera;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Materia;
import ar.edu.unlam.tallerweb1.util.enums.Turno;

public class RepositorioGruposTest extends SpringTest{

	@Autowired
	private RepositorioGrupos repository;
	
	@Test @Transactional @Rollback
	public void testQuePodamosObtenerUnGrupoPorSuID () {
		Long id = givenUnGrupoPersistido();
		
		Grupo buscado = whenBuscoAlGrupoPorSuID(id);
		
		thenObtengoSusDatos(buscado);
	}
	
	@Test @Transactional @Rollback
	public void testQuePodamosModificarGrupo () {
		Long id = givenUnGrupoPersistido();
		
		Grupo formulario = givenDatosAModificar();
		
		whenModificoLosDatosDelGrupo(id, formulario);
		
		thenObtengoSusNuevosDatos(id);
	}
	
	@Test @Transactional @Rollback
	public void testQueSePuedaEliminarUnGrupo () {
		Long id = givenUnGrupoPersistido();
		
		whenEliminoElGrupo(id);
		
		thenYaNoExisteEnPersistencia(id);
	}
	
	private void thenYaNoExisteEnPersistencia(Long id) {
		assertThat(repository.getGrupoByID(id)).isNull();
	}

	private void whenEliminoElGrupo(Long id) {
		Grupo objetivo = session().get(Grupo.class, id);
		repository.eliminarGrupo(objetivo);
	}

	private Grupo givenDatosAModificar() {
		Grupo datos = new Grupo();
		datos.setNombre("Nuevo nombre de grupo");
		return datos;
	}

	private void thenObtengoSusNuevosDatos(Long id) {
		Grupo buscado = session().get(Grupo.class, id);
		assertThat(buscado.getNombre()).isEqualTo("Nuevo nombre de grupo");
	}

	private void whenModificoLosDatosDelGrupo(Long id, Grupo formulario) {
		Grupo objetivo = session().get(Grupo.class, id);
		objetivo.actualizar(formulario);
		
		repository.actualizarGrupo(objetivo);
	}

	private void thenObtengoSusDatos(Grupo buscado) {
		assertThat(buscado.getNombre()).isEqualTo("Hola");
	}

	private Grupo whenBuscoAlGrupoPorSuID(Long id) {
		return repository.getGrupoByID(id);
	}

	private Long givenUnGrupoPersistido() {
		Materia materia = givenExisteUnaMateria();
		Carrera carrera = givenExisteUnaCarrera();
		Grupo grupo = givenExisteUnGrupo();
		
		session().save(carrera);
		session().save(materia);
		
		grupo.setCarrera(carrera);
		grupo.setMateria(materia);
		session().save(grupo);
		
		return grupo.getId();
	}
	
	private Materia givenExisteUnaMateria() {
		Materia nuevaMateria = new Materia();
		
		nuevaMateria.setNombre("Basica 1");
		return nuevaMateria;
	}

	private Carrera givenExisteUnaCarrera() {
		Carrera nuevaCarrera = new Carrera();
		
		nuevaCarrera.setNombre("Desarrollo WEB");
		return nuevaCarrera;
	}
	
	private Grupo givenExisteUnGrupo() {
		Grupo nuevoGrupo = new Grupo();
		
		nuevoGrupo.setCtdMaxima(2);
		nuevoGrupo.setDescripcion("Desc");
		nuevoGrupo.setNombre("Hola");
		nuevoGrupo.setPrivado(true);
		nuevoGrupo.setTurno(Turno.NOCHE);
		
		return nuevoGrupo;
	}

}