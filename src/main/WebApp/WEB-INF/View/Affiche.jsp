<%--
  Created by IntelliJ IDEA.
  User: Andrianiavo
  Date: 20/06/2023
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.frontoffice.Model.*" %>
<%@ page import="java.util.Vector" %>
<%
    Spectacle spect= (Spectacle) request.getAttribute("spectacle");
    Vector<Place>places= (Vector<Place>) request.getAttribute("place");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Affiche</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Asset/css/bootstrap.css"/>
</head>
<body>
<div class="card-body">
    <div class="row my-1">
       <img class="rounded-2 text-center" src="data:image/png;base64,<%=spect.getImage()%>" width="1700" height="100" alt=""/>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th scope="col">nomLieu</th>
                <th scope="col">Nom spectacle</th>
                <th scope="col">dateSpectacle</th>
                <th scope="col">Heur</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><%=spect.getNomLieu()%></td>
                <td><%=spect.getSpectacle()%></td>
                <td><%=Spectacle.dateToDate(spect.getDateSpectacle())%></td>
                <td><%=Spectacle.heur(spect.getHeure())%></td>
            </tr>
            </tbody>
        </table>
    </div>

    <div class="row my-2">
        <div class="col-12">
            <div class="container">
                <h3 class="text-center">Les artistes</h3>
                <div class="row my-1">
                    <table class="table table-striped" border="1">
                        <thead>
                        <tr>
                            <th scope="col">Image artiste</th>
                            <th scope="col">NomArtiiste</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            for (Artiste ar : spect.getArtistes()){ %>
                        <tr>
                            <td ><img class="rounded-2" src="data:image/png;base64,<%=ar.getPhoto()%>" width="70" height="70" alt=""/></td>
                            <td><%=ar.getNomArtiste()%></td>
                        </tr>
                        <%  } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="row my-2">
        <div class="col-12">
            <div class="container">
                <h3 class="text-center">Les Places</h3>
                <div class="row my-1">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">Categorie</th>
                            <th scope="col">Prix</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            for (Place ar : places){ %>
                        <tr>
                            <td><%=ar.getNomCategorie()%></td>
                            <td><%=ar.getPrix()%></td>
                        </tr>
                        <%  } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
