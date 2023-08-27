<%@ page import="java.util.Vector" %>

<%@ page import="com.example.frontoffice.CliniqueModel.Acte" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Vector<Acte> list= (Vector<Acte>) request.getAttribute("actes");
%>
<html>
<head>
    <title>Clinique</title>
    <%@ include file="../Complement/Header.jsp"%>
    <link rel="stylesheet" type="text/css" href="../../../resources/static/Asset/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="../../../resources/static/Asset/css/all.css">
</head>
<body>

<div class="row fixed-top shadow p-3 mb-5 rounded bg-white" style="height: 70px;">
    <div class="row" style="margin-top: 15px; font-size: 30px; ">
        <div class="col-2 offset-10 text-center" style="margin-top: -20px;">
            <h3 class="text-end"></h3>
        </div>
    </div>

</div>
<div class="row" style="width: 100%; position: absolute;top: 72px;margin: 0; background: none;">
    <div class="col-2 shadow-lg">
        <div class="row">
            <%@ include file="../Component/Menu.jsp"%>
        </div>
    </div>
    <div class="col-10">
        <div class="row container">
            <div class="card shadow-lg p-3 mb-5 bg-body rounded" >
                <div class="row">
                    <div class="col-8">
                        <div class="card" style="border: none;">
                            <div class="card-body">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th scope="col">id</th>
                                        <th scope="col">type</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%
                                        for (Acte p : list){ %>
                                    <tr>
                                        <td><%=p.getId()%></td>
                                        <td><%=p.getType()%></td>
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
                                <form action="${pageContext.request.contextPath}/admin/acte" method="post" autocomplete="off">
                                    <div class="mb-3">
                                        <label for="n-20" class="form-label">Nom</label>
                                        <input type="text" class="form-control" id="n-20" name="nom"  aria-label="Default select example">
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
    </div>
</div>


<%@ include file="../Complement/Lien.jsp"%>
<%@ include file="../Complement/Footer.jsp"%>
</body>
</html>