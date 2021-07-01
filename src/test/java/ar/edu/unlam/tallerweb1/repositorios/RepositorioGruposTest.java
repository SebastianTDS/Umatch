package ar.edu.unlam.tallerweb1.repositorios;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.matches;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.Carrera;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.modelo.Materia;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.util.enums.Turno;

public class RepositorioGruposTest extends SpringTest{

	@Autowired
	private RepositorioGrupo repository;
	
	@Test @Transactional @Rollback
	public void testQuePodamosObtenerUnGrupoPorSuID () {
		Usuario admin = givenExisteUnUsuario();
		Long id = givenUnGrupoPersistido(admin).getId();
		
		Grupo buscado = whenBuscoAlGrupoPorSuID(id);
		
		thenObtengoSusDatos(buscado);
	}
	
	@Test @Transactional @Rollback
	public void testQuePodamosModificarGrupo () {
		Usuario admin = givenExisteUnUsuario();
		Long id = givenUnGrupoPersistido(admin).getId();
		
		DatosDeGrupo formulario = givenDatosAModificar();
		
		whenModificoLosDatosDelGrupo(id, formulario);
		
		thenObtengoSusNuevosDatos(id);
	}
	
	@Test @Transactional @Rollback
	public void testQueSePuedaEliminarUnGrupo () {
		Usuario admin = givenExisteUnUsuario();
		Long id = givenUnGrupoPersistido(admin).getId();
		
		whenEliminoElGrupo(id);
		
		thenYaNoExisteEnPersistencia(id);
	}
	
	@Test @Transactional @Rollback
	public void testQueNoBusqueLosGruposALosQuePertenezco () {
		Usuario jorge = givenExisteUnUsuario();
		Usuario manuel = givenExisteUnUsuario();
		
		givenUnGrupoPersistido(jorge);
		
		Grupo grupoB = givenUnGrupoPersistido(manuel);
		
		List<Grupo> encontrados = whenBuscoTodosLosGruposPara(manuel);
		
		thenObtenemosLaCantidadEsperada(encontrados, grupoB);
	}
	
	@Test @Transactional @Rollback
	public void testQueSiNoTengoGrupoTraigaTodos() {
		Usuario jorge = givenExisteUnUsuario();
		Usuario manuel = givenExisteUnUsuario();
		
		givenUnGrupoPersistido(manuel);
		givenUnGrupoPersistido(manuel);
		
		List<Grupo> encontrados = whenBuscoTodosLosGruposPara(jorge);
		
		thenObtenemosLaCantidadEsperada(encontrados);
	}
	
	@Test @Transactional @Rollback
	public void testQueSePuedaBuscarForosDeMateria() {
		givenUnForoPersistido();
		givenUnForoPersistido();
		
		List<Grupo> encontrados = whenBuscoTodosLosForos();
		
		thenObtenemosLaCantidadEsperada(encontrados);
	}
	

	private void givenUnForoPersistido() {
		Materia materia = givenExisteUnaMateria();
		Carrera carrera = givenExisteUnaCarrera();
		Usuario global = new Usuario();
		Grupo foro = givenUnForoDeMateria();
		
		session().save(carrera);
		session().save(materia);
		session().save(global);
		
		foro.setMateria(materia);
		foro.setCarrera(carrera);
		foro.setAdministrador(global);
		
		session().save(foro);
	}

	private List<Grupo> whenBuscoTodosLosForos() {
		return repository.buscarForos();
	}

	private Grupo givenUnForoDeMateria() {
		Grupo nuevoGrupo = new Grupo();
		
		nuevoGrupo.setCantidadMax(0);
		nuevoGrupo.setDescripcion("Foro de Materia");
		nuevoGrupo.setNombre("");
		nuevoGrupo.setCerrado(false);
		nuevoGrupo.setTurno(Turno.NOCHE);
		nuevoGrupo.setEsMateria(true);
		
		return nuevoGrupo;
	}

	private void thenObtenemosLaCantidadEsperada(List<Grupo> encontrados) {
		assertThat(encontrados).isNotNull();
		assertThat(encontrados).hasSize(2);
	}

	private void thenObtenemosLaCantidadEsperada(List<Grupo> encontrados, Grupo noDeseado) {
		assertThat(encontrados).isNotNull();
		assertThat(encontrados).hasSize(1);
		assertThat(encontrados.get(0)).isNotEqualTo(noDeseado);
	}

	private List<Grupo> whenBuscoTodosLosGruposPara(Usuario manuel) {
		return repository.buscarTodos(manuel);
	}

	private void thenYaNoExisteEnPersistencia(Long id) {
		assertThat(repository.getGrupoByID(id)).isNull();
	}

	private void whenEliminoElGrupo(Long id) {
		Grupo objetivo = session().get(Grupo.class, id);
		repository.eliminarGrupo(objetivo);
	}

	private DatosDeGrupo givenDatosAModificar() {
		DatosDeGrupo datos = new DatosDeGrupo();
		datos.setNombre("Nuevo nombre de grupo");
		return datos;
	}

	private void thenObtengoSusNuevosDatos(Long id) {
		Grupo buscado = session().get(Grupo.class, id);
		assertThat(buscado.getNombre()).isEqualTo("Nuevo nombre de grupo");
	}

	private void whenModificoLosDatosDelGrupo(Long id, DatosDeGrupo formulario) {
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

	private Grupo givenUnGrupoPersistido(Usuario administrador) {
		Materia materia = givenExisteUnaMateria();
		Carrera carrera = givenExisteUnaCarrera();
		Grupo grupo = givenExisteUnGrupo();
		
		session().save(carrera);
		session().save(materia);
		session().save(administrador);
		
		grupo.setCarrera(carrera);
		grupo.setMateria(materia);
		grupo.setAdministrador(administrador);
		
		
		grupo.agregarUsuarioAlGrupo(administrador);
		session().save(grupo);
		
		return grupo;
	}
	
	private Usuario givenExisteUnUsuario() {
		Usuario admin = new Usuario();
		
		admin.setNombre("Manuel");
		return admin;
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
		
		nuevoGrupo.setCantidadMax(2);
		nuevoGrupo.setDescripcion("Desc");
		nuevoGrupo.setNombre("Hola");
		nuevoGrupo.setCerrado(true);
		nuevoGrupo.setTurno(Turno.NOCHE);
		
		return nuevoGrupo;
	}

}
