<%@ page import="com.example.frontoffice.CliniqueModel.Facture" %>
<%@ page import="com.example.frontoffice.CliniqueModel.Acte" %>
<%@ page import="com.example.frontoffice.Function.Function" %>
<%@ page import="com.example.frontoffice.Model.Spectacle" %><%--
  Created by IntelliJ IDEA.
  User: Andrianiavo
  Date: 17/07/2023
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Facture f= (Facture) request.getAttribute("facture");
%>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Asset/css/bootstrap.css"/>
    <title>Facture</title>
</head>
<body>
    <div class="">
        <div class="row my-2 text-center">
            <h1>Facture</h1>
        </div>
        <div class="row my-2">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Client : <strong><%=f.getNom()%></strong></th>
                    <th scope="col">ref : <strong><%=f.getReference()%></strong></th>
                </tr>
                <tr>
                    <th scope="col"></th>
                    <th scope="col">Date : <strong><%=Spectacle.dateToDate(f.getDate())%></strong></th>
                </tr>
                </thead>
            </table>
        </div>
        <div class="row my-2">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Acte</th>
                    <th scope="col">montant</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (Acte p : f.getActes()){ %>
                <tr>
                    <td><%=p.getType()%></td>
                    <td><%=Function.affichageDouble(p.getMontant())%></td>
                </tr>
                <% }%>
                </tbody>
            </table>
        </div>
        <div class="row my-2">
            <h2>Total : <strong><%=Function.affichageDouble(f.montant())%></strong></h2>
        </div>
    </div>
</body>
</html>
