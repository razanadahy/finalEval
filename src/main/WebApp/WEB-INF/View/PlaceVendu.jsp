<%@ page import="java.util.Vector" %>
<%@ page import="com.example.frontoffice.Model.Spectacle" %>
<%@ page import="com.example.frontoffice.Model.Place" %><%
    Vector<Spectacle>spectacles= (Vector<Spectacle>) request.getAttribute("list");
    Vector<Place>places=Place.listPlaceVendu();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="Complement/Header.jsp"%>
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
<div class="row " style="width: 100%; position: absolute;top: 300px;margin: 0; background: none;">
    <div class="card col-10 offset-1 shadow-lg p-3 mb-5 bg-body rounded" >
        <div class="row">
            <div class="col-8">
                <div class="card" style="border: none;">
                    <div class="card-body">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">NomLieu</th>
                                <th scope="col">NomSpectacle</th>
                                <th scope="col">Categorie</th>
                                <th scope="col">Nombre</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                for (Place pl : places){ %>
                            <tr>
                                <td><%=pl.getNomLieu()%></td>
                                <td><%=pl.getClient()%></td>
                                <td><%=pl.getNomCategorie()%></td>
                                <td><%=pl.getPrix()%></td>
                            </tr>
                            <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-4">
                <div class="card">
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/insertPlaceVendu" method="post" autocomplete="off">
                            <div class="mb-3">
                                <label for="n-20" class="form-label">Spectacle</label>
                                <select id="n-20" name="spectacle" class="form-select" aria-label="Default select example">
                                    <%
                                        for(Spectacle lieu : spectacles){ %>
                                    <option value="<%=lieu.getIdSpectacle()%>"><%=lieu.getSpectacle()%></option>
                                    <%  } %>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="n-11" class="form-label">Categorie</label>
                                <select id="n-11" name="cat" class="form-select" aria-label="Default select example">
                                    <option value="1">VIP</option>
                                    <option value="2">Reserve</option>
                                    <option value="3">normal</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="exampleInputEmail1" class="form-label">Nombre</label>
                                <input type="number" step="any" class="form-control" name="nom" id="exampleInputEmail1" aria-describedby="emailHelp">
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
                        <%
                            if(request.getAttribute("erreur")!=null){ %>
                                <h2>Place max<%=request.getAttribute("erreur")%></h2>
                          <%  }%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<%@ include file="Complement/Lien.jsp"%>
<%@ include file="Complement/Footer.jsp"%>
</body>
</html>
