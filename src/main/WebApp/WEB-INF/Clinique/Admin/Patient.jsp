<%@ page import="java.util.Vector" %>
<%@ page import="com.example.frontoffice.CliniqueModel.Patient" %>
<%@ page import="com.example.frontoffice.Model.Spectacle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Vector<Patient> list= (Vector<Patient>) request.getAttribute("patients");
%>
<html>
<head>
  <title>Title</title>
  <%@ include file="../Complement/Header.jsp"%>
  <link rel="stylesheet" type="text/css" href="../../../resources/static/Asset/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="../../../resources/static/Asset/css/all.css">
</head>
<body>

<div class="row fixed-top shadow p-3 mb-5 rounded" style="height: 70px; background: #1176a1;">
  <div class="row" style="margin-top: 15px; font-size: 30px; ">
    <div class="col-4">
      <i type="button"  data-bs-toggle="modal" data-bs-target="#staticBackdrop" class="fa-solid fa-bars fa-lg"></i>
    </div>
    <div class="col-2 offset-10 text-center" style="margin-top: -20px;">
      <h3 class="text-end">Clinique</h3>
    </div>
  </div>

</div>
<div class="row"
     style="background-image: url('${pageContext.request.contextPath}/Asset/img/fond.jpg');
             height: 400px;width: 100%;margin: 0;  position: absolute;
             background-size: cover;
             background-repeat: no-repeat;
             left: 0;
             opacity: 12;">
</div>
<div class="row " style="width: 100%; position: absolute;top: 300px;margin: 0; background: none;">
  <div class="card col-10 offset-1 shadow-lg p-3 mb-5 bg-body rounded" >
    <div class="row">
      <div class="col-8">
        <div class="card" style="border: none;">
          <div class="card-body">
            <table class="table">
              <thead>
              <tr>
                <th scope="col">Nom</th>
                <th scope="col">naissance</th>
                <th scope="col">genre</th>
                <th scope="col">rembourser</th>
                <th scope="col"></th>
              </tr>
              </thead>
              <tbody>
              <%
                for (Patient p : list){ %>
              <tr>
                <td><%=p.getNom()%></td>
                <td><%=Spectacle.dateToDate(p.getNaissance())%></td>
                <td><%=p.getIdGenre()%></td>
                <td><%=p.isRembourche()%></td>
                <td>
                  <button onclick="showModal('<%=p.getIdPatient()%>','<%=p.getNom()%>','<%=p.getIdGenre()%>','<%=p.getNaissance()%>','<%=p.isRembourche()%>')" type="button" class="btn btn-secondary">Modifier</button>&nbsp;&nbsp;&nbsp;&nbsp;
                </td>
              </tr>
              <% }%>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div class="col-4">
        <div class="card">
          <div class="card-body">
            <form action="${pageContext.request.contextPath}/admin/patient" method="post" autocomplete="off">
              <div class="mb-3">
                <label for="n-20" class="form-label">Nom</label>
                <input type="text" class="form-control" id="n-20" name="nom"  aria-label="Default select example">
              </div>
              <div class="mb-3">
                <label for="n-11" class="form-label">Genre</label>
                <select id="n-11" name="genre" class="form-select" aria-label="Default select example">
                  <option value="1">Homme</option>
                  <option value="2">Femme</option>
                </select>
              </div>
              <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label">Date</label>
                <input type="date"  class="form-control" name="date" id="exampleInputEmail1" aria-describedby="emailHelp">
              </div>
              <div class="mb-3">
                <label for="n-156" class="form-label">remboursement</label>
                <select id="n-156" name="rem" class="form-select" aria-label="Default select example">
                  <option value="true">true</option>
                  <option value="false">false</option>
                </select>
              </div>
              <div class="row">
                <div class="col-6">
                  <button type="submit" class="btn btn-primary" style="width: 100%">Inserer</button>
                </div>
                <div class="col-6">
                  <button type="reset" class="btn btn-primary" style="width: 100%">Rejeter</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>


<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Modifier</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="${pageContext.request.contextPath}/admin/patientModif" method="post" autocomplete="off">
          <input type="hidden" name="id" value="0" id="id">
          <div class="mb-3">
            <label for="n-nom" class="form-label">Nom</label>
            <input type="text" class="form-control" id="n-nom" name="nom"  aria-label="Default select example">
          </div>
          <div class="mb-3">
            <label for="n-genre" class="form-label">Genre</label>
            <select id="n-genre" name="genre" class="form-select" aria-label="Default select example">
              <option value="1">Homme</option>
              <option value="2">Femme</option>
            </select>
          </div>
          <div class="mb-3">
            <label for="n-date" class="form-label">Date</label>
            <input type="date"  class="form-control" name="date" id="n-date" aria-describedby="emailHelp">
          </div>
          <div class="mb-3">
            <label for="n-rem" class="form-label">remboursement</label>
            <select id="n-rem" name="rem" class="form-select" aria-label="Default select example">
              <option value="true">true</option>
              <option value="false">false</option>
            </select>
          </div>
          <div class="row">
            <div class="col-6">
              <button type="submit" class="btn btn-primary" style="width: 100%">Inserer</button>
            </div>
            <div class="col-6">
              <button type="reset" class="btn btn-primary" style="width: 100%">Rejeter</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<%@ include file="../Complement/Lien.jsp"%>
<%@ include file="../Complement/Footer.jsp"%>
</body>
</html>
