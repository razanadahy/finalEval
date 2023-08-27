
<%@ page import="java.util.Vector" %>
<%@ page import="com.example.frontoffice.Model.Autre" %>
<%@ page import="com.example.frontoffice.Controller.MainController" %>
<%
    Vector<Autre>list= Autre.list();
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
<div class="row " style="width: 100%; position: absolute;top: 300px;margin: 0; background: none;">
    <div class="card col-10 offset-1 shadow-lg p-3 mb-5 bg-body rounded" >
        <div class="row">
            <div class="col-8">
                <div class="card" style="border: none;">
                    <div class="card-body">
                        <div class="row my-2">
                            <h3 class="text-center">Liste des autres depenses</h3>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">id</th>
                                    <th scope="col">Nom</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    for (Autre lieu : list){%>
                                <tr>
                                    <th scope="row"><%=lieu.getIdAutre()%></th>
                                    <td><%=lieu.getNomAutre()%></td>
                                </tr>
                                <% } %>
                                </tbody>
                            </table>
                        </div>
                        <hr>
                        <div class="row my-2">
                            <h3 class="text-center">Autres engagés</h3>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">id</th>
                                    <th scope="col">Montant</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    for (Autre lieu : MainController.autre){%>
                                <tr>
                                    <th scope="row"><%=lieu.getIdAutre()%></th>
                                    <td><%=lieu.getMontant()%></td>
                                </tr>
                                <% } %>
                                </tbody>
                            </table>
                        </div>
                        <hr>
                    </div>
                </div>
            </div>
            <div class="col-4">
                <div class="row my-4">
                    <div class="card">
                        <div class="card-body">
                            <form action="${pageContext.request.contextPath}/modifAutre" method="post" autocomplete="off">
                                <div class="mb-3">
                                    <label for="n-01" class="form-label">Autre depense</label>
                                    <select id="n-01" name="artiste" class="form-select" aria-label="Default select example">
                                        <%
                                            for(Autre lieu : list){ %>
                                        <option value="<%=lieu.getIdAutre()%>"><%=lieu.getNomAutre()%></option>
                                        <%  } %>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="n-4" class="form-label">Montant</label>
                                    <input type="number" step="any" class="form-control" name="montant" id="n-4" aria-describedby="emailHelp">
                                </div>
                                <div class="row">
                                    <div class="col-6">
                                        <button type="submit" class="btn btn-primary" style="width: 100%">Inserer</button>
                                    </div>
                                    <div class="col-6">
                                        <a href="${pageContext.request.contextPath}/validerModif" type="button" class="btn btn-primary" style="width: 100%">Valider</a>
                                    </div>
                                </div>
                            </form>
                        </div>
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
