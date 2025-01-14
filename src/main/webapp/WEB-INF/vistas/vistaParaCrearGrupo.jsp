<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="placeholder" uri="http://www.springframework.org/tags/form" %>
<%@ page import="ar.edu.unlam.tallerweb1.util.enums.Turno" %>
<%@ page import="ar.edu.unlam.tallerweb1.util.enums.Privacidad" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/templates/head.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/templates/headerLogueado.jsp"></jsp:include>
<main>

  <div class="container-fluid">

    <div class=" d-flex flex-wrap justify-content-center row m-5">
        <c:if test="${error != null}">
            <div class="col-12 mt-3 alert alert-danger" role="alert" style="max-height:57px;">
                ${error}
            </div>
        </c:if>

      <div class=" col-12 col-sm-12 col-md-12 col-lg-6 col-xl-6  mt-5">


        <img src="img/crear_grupo.svg" class=" img-fluid ilustraciones-index mt-5" alt="imagen">

      </div>


      <div class="col-12 col-sm-12 col-md-12 col-lg-6 col-xl-6 ">

        <div class="containerr">
          <div class="title">Crea tu equipo</div>
          <div class="content">
            <form:form action="crear-grupo" method="POST" modelAttribute="datos">
            <div class="user-details">
              <div class="input-box">
                <span class="details">Nombre del equipo</span>
                <form:input path="nombre" id="nombre" type="text"  class="form-control form-control-sm" placeholder="Ingrese un nombre"/>
              </div>
              <div class="input-box">
                <span class="details">Descripcion del grupo</span>
                <form:input path="descripcion" id="descripcion" type="textarea"  class="form-control form-control-sm" placeholder="Ingrese una descripcion"/>
              </div>
              <div class="input-box">
                <span class="details">Cantidad De Integrantes</span>
                <form:input path="cantidadMax" id="cantidadMax" type="number" class="form-control form-control-sm" placeholder="Ingrese un numero"/>
              </div>
              <div class="input-box">
                <span class="details mb-3">Carrera</span>
              <form:select path="carrera" class="form-control form-control-sm">
                <c:forEach items="${carreras}" var="carrera">
                  <option value="${carrera.id}">${carrera.nombre}</option>
                </c:forEach>
              </form:select>
              </div>
                <div class="input-box">
                  <span class="details">Materia</span>
              <form:select path="materia" id="materia" class="form-control form-control-sm">
                <c:forEach items="${materias}" var="materia">
                  <option value="${materia.id}">${materia.nombre}</option>
                </c:forEach>
              </form:select>
                </div>
                <div class="input-box">
                  <span class="details">Turno</span>
	              <form:select path="turno" id="turno" class="form-control form-control-sm">
		               <option value="<%=Turno.MANIANA%>">Ma�ana</option>
		               <option value="<%=Turno.TARDE%>">Tarde</option>
		               <option value="<%=Turno.NOCHE%>">Noche</option>
	              </form:select>
                </div>
                <div>
                	<div class="details mb-1">Privacidad:</div>
               		<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="privacidad" id="abierto" value="<%=Privacidad.ABIERTO%>"/>
						<label class="form-check-label"for="abierto">Abierto</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="privacidad" id="cerrado" value="<%=Privacidad.CERRADO%>"/>
						<label class="form-check-label" for="cerrado">Cerrado</label>
					</div>
                </div>
                
              <button class="btn btn-outline-primary" Type="Submit">Crear !</button>
              </form:form>
            </div>
          </div>
        </div>
      </div>

    </div>
  </div>

</main>
<jsp:include page="/templates/footer.jsp"></jsp:include>
</body>

</html>