<%@ page import="java.util.Vector" %>

<%@ page import="com.example.frontoffice.CliniqueModel.Depense" %>
<%@ page import="com.example.frontoffice.Model.Spectacle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Vector<Depense> list= (Vector<Depense>) request.getAttribute("dept");
    Vector<Depense>montantDept=(Vector<Depense>) request.getAttribute("deptMontant");
    Vector<String>erreur= (Vector<String>) request.getAttribute("erreur");
    Vector<String>diso= (Vector<String>) request.getAttribute("diso");
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
            <div class="col-7">
                <div class="card" style="border: none;">

                    <div class="card-body">
                        <%
                            if (erreur!=null){ %>
                        <div class="row bg-danger">
                            <%
                                for (String p : erreur){ %>
                                    <span><%=p%></span>
                            <%}%>
                        </div>
                        <% }%>
                        <%
                            if (diso!=null ){
                        %>
                        <div class="row bg-danger">
                            <%
                                for (String p : diso){ %>
                            <span><%=p%></span>
                            <%}%>
                        </div>
                        <% }%>
                        <div class="row">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">type</th>
                                    <th scope="col">montant</th>
                                    <th scope="col">Date</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    for (Depense p : montantDept){ %>
                                <tr>
                                    <td><%=p.getType()%></td>
                                    <td><%=p.getMontant()%></td>
                                    <td><%=Spectacle.dateToDate(p.getDate())%></td>
                                </tr>
                                <% }%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-5">
                <div class="card">
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/utilisateur/dept" method="post" autocomplete="off">
                            <div class="mb-3">
                                <label for="n-201" class="form-label">Nom</label>
                                <select id="n-201" name="dept" class="form-select" aria-label="Default select example">
                                    <%
                                        for (Depense p : list){ %>
                                    <option value="<%=p.getId()%>"><%=p.getType()%></option>
                                    <% }%>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="n-202" class="form-label">Montant</label>
                                <input type="number" step="any" class="form-control" id="n-202" name="montant"  aria-label="Default select example">
                            </div>

                            <div class="mb-3">
                                <label for="n-2021" class="form-label">Date</label>
                                <input type="date"  class="form-control" id="n-2021" name="date"  aria-label="Default select example">
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
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/utilisateur/multiple" method="post" autocomplete="off">
                            <div class="mb-3">
                                <label for="n-201" class="form-label">Nom</label>
                                <input type="text"  class="form-control" id="n-201" name="dept"  aria-label="Default select example">
                            </div>
                            <div class="mb-3">
                                <label for="n-202" class="form-label">Montant</label>
                                <input type="text"  class="form-control" id="n-202" name="montant"  aria-label="Default select example">
                            </div>
                            <div class="mb-3">
                                <label for="n-2021" class="form-label">Jour</label>
                                <input type="number"  class="form-control" id="n-2021" name="jour"  aria-label="Default select example">
                            </div>
                            <div class="mb-3">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" name="mois" type="checkbox" id="inlineCheckbox1" value="01">
                                    <label class="form-check-label" for="inlineCheckbox1">Jan</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" name="mois" id="inlineCheckbox2" value="02">
                                    <label class="form-check-label" for="inlineCheckbox2">fev</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" name="mois" id="inlineCheckbox1" value="03">
                                    <label class="form-check-label" for="inlineCheckbox1">Mar</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="inlineCheckbox2" name="mois" value="04">
                                    <label class="form-check-label" for="inlineCheckbox2">Apr</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="inlineCheckbox1" name="mois" value="05">
                                    <label class="form-check-label" for="inlineCheckbox1">Mai</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="inlineCheckbox2" name="mois" value="06">
                                    <label class="form-check-label" for="inlineCheckbox2">Jun</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="inlineCheckbox1" name="mois" value="07">
                                    <label class="form-check-label" for="inlineCheckbox1">Jul</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="inlineCheckbox2" name="mois" value="08">
                                    <label class="form-check-label" for="inlineCheckbox2">Aout</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="inlineCheckbox1" name="mois" value="09">
                                    <label class="form-check-label" for="inlineCheckbox1">Sept</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="inlineCheckbox2" name="mois" value="10">
                                    <label class="form-check-label" for="inlineCheckbox2">ock</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="inlineCheckbox1" name="mois" value="11">
                                    <label class="form-check-label" for="inlineCheckbox1">Nov</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="inlineCheckbox2" name="mois" value="12">
                                    <label class="form-check-label" for="inlineCheckbox2">Dec</label>
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="n-2021" class="form-label">Anne</label>
                                <input type="number"  class="form-control" id="n-2021" name="anne"  aria-label="Default select example">
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
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/utilisateur/file" method="post" autocomplete="off" enctype="multipart/form-data">
                            <div class="input-group">
                                <input type="file" class="form-control" name="file" accept="text/csv" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04" aria-label="Upload">
                                <button class="btn btn-primary" type="submit" id="inputGroupFileAddon04">inserer</button>
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
