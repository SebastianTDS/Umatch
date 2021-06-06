<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="placeholder" uri="http://www.springframework.org/tags/form" %>
<%@ page import="ar.edu.unlam.tallerweb1.util.enums.Disponibilidad" %>
<%@ page import="ar.edu.unlam.tallerweb1.util.enums.Privacidad" %>
<%@ page import="ar.edu.unlam.tallerweb1.util.enums.Turno" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/templates/head.jsp"></jsp:include>
    <!-- MDB -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.5.0/mdb.min.css" rel="stylesheet"/>
</head>

<body>
<jsp:include page="/templates/headerLogueado.jsp"></jsp:include>

<main style="min-height: 500px">

    <div class="container-fluid row ">
        <div class="col-5 col-sm-4 col-md-3 col-lg-2 col-xl-2 container-fluid"
             style="min-height: 500px">
            <jsp:include page="/templates/nav.jsp"></jsp:include>
        </div>
        <div class="col-7 col-sm-8 col-md-9 col-lg-10 col-xl-10 border-start row container-fluid"
             style="min-height: 500px">

            <div class="col-12 col-sm-12 col-md-12 col-lg-12 clo-xl-12 container-fluid row d-flex justify-content-center flex-wrap align-content-center">
                <form:form action="buscar-grupos" method="POST" modelAttribute="datosParaBuscarUnGrupo"
                           class="row text-center">
                    <h3 class="form-signin-heading">Busqueda de grupos</h3>
                    <div class="col-6 col-sm-4 col-md-4 col-lg-4 col-xl-2  p-2">
                        <form:select path="carrera" id="carrera" class="form-control form-control-sm">
                            <option value="">Carreras</option>

                            <c:forEach items="${carreras}" var="carrera">
                                <option value="${carrera.id}">${carrera.nombre}</option>
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="col-6 col-sm-4 col-md-4 col-lg-4 col-xl-2  p-2">
                        <form:select path="materia" id="materia" class="form-control form-control-sm">
                            <option value="">Materias</option>

                            <c:forEach items="${materias}" var="materia">
                                <option value="${materia.id}">${materia.nombre}</option>
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="col-6 col-sm-4 col-md-4 col-lg-4 col-xl-2  p-2">
                        <form:select path="turno" id="turno" class="form-control form-control-sm">
                            <option value="">Turno</option>
                            <option value="<%=Turno.MANIANA%>">Ma�ana</option>
                            <option value="<%=Turno.TARDE%>">Tarde</option>
                            <option value="<%=Turno.NOCHE%>">Noche</option>

                        </form:select>
                    </div>
                    <div class="col-6 col-sm-4 col-md-4 col-lg-4 col-xl-2  p-2">
                        <form:select path="privacidad" id="privacidad" class="form-control form-control-sm">
                            <option value="">Privacidad</option>
                            <option value="<%=Privacidad.ABIERTO%>">Abierto</option>
                            <option value="<%=Privacidad.CERRADO%>">Cerrado</option>
                        </form:select>
                    </div>
                    <div class="col-6 col-sm-4 col-md-4 col-lg-4 col-xl-2  p-2">
                        <form:select path="disponibilidad" id="disponibilidad" class="form-control form-control-sm">
                            <option value="">Disponibilidad</option>
                            <option value="<%=Disponibilidad.DISPONIBLE%>">Disponible</option>
                            <option value="<%=Disponibilidad.LLENO%>">Lleno</option>
                        </form:select>
                    </div>
                    <div class="col-6 col-sm-4 col-md-4 col-lg-4 col-xl-2  p-2">
                        <form:input path="nombre" id="nombre" type="text" class="form-control form-control-sm"
                                    placeholder="Nombre"/>
                    </div>
                    <div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12  p-1">
                        <button class="btn btn-lg btn btn-primary btn-sm" Type="Submit">Buscar</button>
                    </div>
                </form:form>
            </div>

            <div class="col-12 col-sm-12 col-md-12 col-lg-12 clo-xl-12 container-fluid row d-flex justify-content-center ">
                <c:if test="${not empty grupos}">
                    <c:forEach items="${grupos}" var="grupo">
                        <div class="bg-light text-dark col-12 col-sm-6 col-md-5 col-lg-4 col-xl-3 m-3 d-flex flex-wrap align-content-between hover-shadow bg-body rounded">
                            <div class="card-body container-fluid">
                                <h5 class="card-title text-center">${grupo.nombre}</h5>
                                <p class="card-text">${grupo.carrera.nombre}</p>
                                <p class="card-text">${grupo.materia.nombre}</p>
                                <div>
                                    <div>
                                        <p class="card-text">${grupo.turno}</p>
                                        <c:if test="${grupo.cerrado==true}">
                                            <p class="card-text">Cerrado</p>
                                        </c:if>
                                        <c:if test="${grupo.cerrado==false}">
                                            <p class="card-text">Abierto</p>
                                        </c:if>
                                    </div>
                                    <div class="text-center container m-1">
                                        <img src="img/Logosolo.ico" style="width: 80px">
                                    </div>
                                </div>
                                <div class="d-flex justify-content-center m-3">
                                    <button type="submit" class="btn btn-success mt-3" name="id" form="unirte"
                                            value="${grupo.id}">Unirte
                                    </button>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <form action="grupos/ingresar-a-grupo" id="unirte" method="POST"></form>
                </c:if>
                <c:if test="${not empty error}">
                    ${error}
                </c:if>
            </div>
        </div>
    </div>
</main>

<jsp:include page="/templates/footer.jsp"></jsp:include>

</body>

</html>