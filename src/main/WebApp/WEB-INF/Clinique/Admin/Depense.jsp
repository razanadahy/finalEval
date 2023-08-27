<%@ page import="java.util.Vector" %>
<%@ page import="com.example.frontoffice.CliniqueModel.Depense" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Vector<Depense> list= (Vector<Depense>) request.getAttribute("depts");
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
                                <th scope="col">id</th>
                                <th scope="col">type</th>
                                <th scope="col">ref</th>
                                <th scope="col">budget annuelle</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                for (Depense p : list){ %>
                            <tr>
                                <td><%=p.getId()%></td>
                                <td><%=p.getType()%></td>
                                <td><%=p.getRef()%></td>
                                <td><%=p.getBudget()%></td>
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
                        <form action="${pageContext.request.contextPath}/admin/depense" method="post" autocomplete="off">
                            <div class="mb-3">
                                <label for="n-20" class="form-label">Nom</label>
                                <input type="text" class="form-control" id="n-20" name="nom"  aria-label="Default select example">
                            </div>
                            <div class="mb-3">
                                <label for="n-202" class="form-label">ref</label>
                                <input type="text" class="form-control" id="n-202" name="ref"  aria-label="Default select example">
                            </div>
                            <div class="mb-3">
                                <label for="n-203" class="form-label">budget annuelle</label>
                                <input type="number" step="any" class="form-control" id="n-203" name="montant"  aria-label="Default select example">
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



<%@ include file="../Complement/Lien.jsp"%>
<%@ include file="../Complement/Footer.jsp"%>
</body>
</html>
