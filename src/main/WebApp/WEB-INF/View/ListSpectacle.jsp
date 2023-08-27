<%@ page import="java.util.Vector" %>
<%@ page import="com.example.frontoffice.Model.*" %>
<%
  Vector<Spectacle>spectacles= (Vector<Spectacle>) request.getAttribute("list");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <%@ include file="Complement/Header.jsp"%>
</head>
<body>

<div class="row fixed-top shadow p-3 mb-5 rounded" style="height: 70px; background: #1176a1;">
  <div class="row" style="margin-top: 15px; font-size: 30px; ">
    <div class="col-4">
      <i type="button"  data-bs-toggle="modal" data-bs-target="#staticBackdrop" class="fa-solid fa-bars fa-lg"></i>
    </div>
    <div class="col-2 offset-10 text-center" style="margin-top: -20px;">
      <h3 class="text-end">Evenement</h3>
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
<div class="row " style="width: 100%; position: absolute;top: 100px;margin: 0; background: none;">
  <div class="card col-10 offset-1 shadow-lg p-3 mb-5 bg-body rounded" >
    <div class="row">
      <div class="col-12">
        <div class="card" style="border: none;">
          <%
            for (Spectacle spect : spectacles){%>
          <div class="card-body">
            <div class="row my-1">
              <table class="table">
                <thead>
                <tr>
                  <th scope="col">Spectacle</th>
                  <th scope="col">nomLieu</th>
                  <th scope="col">dateSpectacle</th>
                  <th scope="col">Heur</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td><%=spect.getSpectacle()%></td>
                  <td><%=spect.getNomLieu()%></td>
                  <td><%=spect.getDateSpectacle().toString()%></td>
                  <td><%=spect.getHeure()%></td>
                </tr>
                </tbody>
              </table>
            </div>
            <div class="row my-1">
              <a href="${pageContext.request.contextPath}/modifCreation?idSpectacle=<%=spect.getIdSpectacle()%>" type="button" class="btn btn-secondary" >Modifier</a>
            </div>
            <hr>
            <div class="row my-2">
              <div class="col-6">
                <div class="container">
                  <h3 class="text-center">Sonorisation</h3>
                  <div class="row my-1">
                    <table class="table">
                      <thead>
                      <tr>
                        <th scope="col">id</th>
                        <th scope="col">Nom</th>
                        <th scope="col">type</th>
                        <th scope="col">Valeur</th>
                      </tr>
                      </thead>
                      <tbody>
                      <%
                        for (Sonorisation art : spect.getSonorisations()){ %>
                      <tr>
                        <th scope="row"><%=art.getIdSono()%></th>
                        <td><%=art.getNomSono()%></td>
                        <td><%=art.getNomType()%></td>
                        <td><%=art.getTarif()%></td>
                      </tr>
                      <%} %>
                      </tbody>
                    </table>
                  </div>
                  <div class="row my-4">
                    <a href="${pageContext.request.contextPath}/modifSono?idSpectacle=<%=spect.getIdSpectacle()%>" type="button" class="btn btn-secondary" >Modifier</a>
                  </div>
                </div>
              </div>
              <div class="col-6">
                <div class="container">
                  <h3 class="text-center">Logistique</h3>
                  <div class="row my-1">
                    <table class="table">
                      <thead>
                      <tr>
                        <th scope="col">id</th>
                        <th scope="col">Nom</th>
                        <th scope="col">type</th>
                        <th scope="col">Valeur</th>
                      </tr>
                      </thead>
                      <tbody>
                      <%
                        for (Logistique art : spect.getLogistiques()){ %>
                      <tr>
                        <th scope="row"><%=art.getIdLogistique()%></th>
                        <td><%=art.getNomLogistique()%></td>
                        <td><%=art.getNomType()%></td>
                        <td><%=art.getTarif()%></td>
                      </tr>
                      <%} %>
                      </tbody>
                    </table>
                  </div>
                  <div class="row my-4">
                    <a href="${pageContext.request.contextPath}/modifLogistique?idSpectacle=<%=spect.getIdSpectacle()%>" type="button" class="btn btn-secondary" >Modifier</a>
                  </div>
                </div>
              </div>
            </div>
            <hr>

            <div class="row my-2">
              <div class="col-6">
                <div class="container">
                  <h3 class="text-center">Les artistes</h3>
                  <div class="row my-1">
                    <table class="table">
                      <thead>
                      <tr>
                        <th scope="col">NomArtiiste</th>
                        <th scope="col">Montant</th>
                      </tr>
                      </thead>
                      <tbody>
                      <%
                        for (Artiste ar : spect.getArtistes()){ %>
                      <tr>
                        <td><%=ar.getNomArtiste()%></td>
                        <td><%=ar.getTarif()%></td>
                      </tr>
                      <%  } %>
                      </tbody>
                    </table>
                  </div>
                  <div class="row my-4">
                    <a href="${pageContext.request.contextPath}/modifArtiste?idSpectacle=<%=spect.getIdSpectacle()%>" type="button" class="btn btn-secondary" >Modifier</a>
                  </div>
                </div>
              </div>
              <div class="col-6">
                <div class="container">
                  <h3 class="text-center">Autre depense</h3>
                  <div class="row my-1">
                    <table class="table">
                      <thead>
                      <tr>
                        <th scope="col">Nom autre</th>
                        <th scope="col">Montant</th>
                      </tr>
                      </thead>
                      <tbody>

                      <%
                        for (Autre aj : spect.getDepenses()){ %>
                      <tr>
                        <td><%=aj.getNomAutre()%></td>
                        <td><%=aj.getMontant()%></td>
                      </tr>
                      <%  } %>
                      </tbody>
                    </table>
                  </div>
                  <div class="row my-4">
                    <a href="${pageContext.request.contextPath}/modifAutre?idSpectacle=<%=spect.getIdSpectacle()%>" type="button" class="btn btn-secondary" >Modifier</a>
                  </div>
                </div>
              </div>
            </div>
            <hr>
            <div class="row my-4">
              <div class="col-6">
                <div class="row">
                  <a href="${pageContext.request.contextPath}/export-pdf?id=<%=spect.getIdSpectacle()%>" type="button" class="btn btn-primary" >Exporter en PDF</a>
                </div>
              </div>
              <div class="col-6">
                <div class="row">
                  <button onclick="showModal('<%=spect.getIdSpectacle()%>')" type="button" class="btn btn-primary" >Bis</button>
                </div>
              </div>
            </div>
          </div>
           <% } %>
        </div>
      </div>
    </div>
  </div>
</div>


<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Modifier date</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="${pageContext.request.contextPath}/duplicate" method="post" autocomplete="off">
          <input type="hidden" id="id" name="id" value="0">

          <div class="mb-3">
            <label for="exampleInputEmail" class="form-label">Date</label>
            <input type="Date"  class="form-control" name="nom" id="exampleInputEmail" aria-describedby="emailHelp">
          </div>
          <div class="mb-3">
            <label for="exampleInputEmail" class="form-label">Heure</label>
            <input type="number" step="any" class="form-control" name="heur" id="exampleInputEmail" aria-describedby="emailHelp">
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
<%@ include file="Complement/Lien.jsp"%>
<%@ include file="Complement/Footer.jsp"%>
<script src="${pageContext.request.contextPath}/Asset/js/Modifier.js"></script>
</body>
</html>
