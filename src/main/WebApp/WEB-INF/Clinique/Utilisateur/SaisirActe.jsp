<%@ page import="java.util.Vector" %>
<%@ page import="com.example.frontoffice.CliniqueModel.Patient" %>
<%@ page import="com.example.frontoffice.CliniqueModel.Facture" %>
<%@ page import="com.example.frontoffice.CliniqueModel.Acte" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Vector<Patient> patients= (Vector<Patient>) request.getAttribute("patients");
  Vector<Facture>factures= (Vector<Facture>) request.getAttribute("factures");
  Vector<Acte>acte= (Vector<Acte>) request.getAttribute("actes");
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
          <%
            for (Facture f : factures){%>
          <div class="card-body">
            <div class="row">
              <table class="table">
                <thead>
                <tr>
                  <th scope="col">Ref</th>
                  <th scope="col">Date</th>
                  <th scope="col">Client</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td ><%=f.getReference()%></td>
                  <td ><%=f.getDate()%></td>
                  <td ><%=f.getNom()%></td>
                </tr>
                </tbody>
              </table>
            </div>
            <div class="row">
              <table class="table">
                <thead>
                <tr>
                  <th scope="col">Acte</th>
                  <th scope="col">Montant</th>
                </tr>
                </thead>
                <tbody>
                  <%
                    for (Acte a : f.getActes()){%>
                  <tr>
                    <td><%=a.getType()%></td>
                    <td><%=a.getMontant()%></td>
                  </tr>
                  <%}%>
                </tbody>
              </table>
            </div>
            <div class="row">
              <div class="col-6">
                <button onclick="ajouter('<%=f.getIdFacture()%>')" type="button" class="btn btn-primary" style="width: 100%;">Ajouter un Acte</button>&nbsp;&nbsp;&nbsp;&nbsp;
              </div>
              <div class="col-6">
                <a href="${pageContext.request.contextPath}/utilisateur/export-pdf?id=<%=f.getIdFacture()%>"  class="btn btn-primary" style="width: 100%">Export pdf</a>
              </div>
            </div>
          </div>
          <hr>
          <% }%>
        </div>
      </div>
      <div class="col-4">
        <div class="card">
          <div class="card-body">
            <form action="${pageContext.request.contextPath}/utilisateur/" method="post" autocomplete="off">
              <div class="mb-3">
                <label for="n-20" class="form-label">Reference</label>
                <input type="text" class="form-control" id="n-20" name="nom"  aria-label="Default select example">
              </div>
              <div class="mb-3">
                <label for="n-11" class="form-label">Patient</label>
                <select id="n-11" name="idPatient" class="form-select" aria-label="Default select example">
                  <%
                    for (Patient p : patients){ %>
                  <option value="<%=p.getIdPatient()%>"><%=p.getNom()%></option>
                  <% }%>
                </select>
              </div>
              <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label">Date</label>
                <input type="date"  class="form-control" name="date" id="exampleInputEmail1" aria-describedby="emailHelp">
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
        <h1 class="modal-title fs-5" id="exampleModalLabel">Ajouter un acte</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="${pageContext.request.contextPath}/utilisateur/ajouterActe" method="post" autocomplete="off">
          <input type="hidden" name="id" value="0" id="id">
          <div class="mb-3">
            <label for="n-209" class="form-label">Montant</label>
            <input type="number" step="any" class="form-control" id="n-209" name="montant"  aria-label="Default select example">
          </div>
          <div class="mb-3">
            <label for="n-115" class="form-label">Acte</label>
            <select id="n-115" name="acte" class="form-select" aria-label="Default select example">
              <%
                for (Acte p : acte){ %>
              <option value="<%=p.getId()%>"><%=p.getType()%></option>
              <% }%>
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
