<%@ page import="java.util.Vector" %>
<%@ page import="com.example.frontoffice.Model.Lieu" %><%
    Vector<Lieu>lieus= null;
    try {
        lieus = Lieu.listLieu();
    } catch (Exception e) {
        e.printStackTrace();
    }
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
            <div class="col-8">
                <div class="card" style="border: none;">
                    <div class="card-body">
                        <div class="row my-4">
                            <h3 class="text-center my-3">Lieu</h3>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">id</th>
                                    <th scope="col">Nom</th>
                                    <th scope="col">type</th>
                                    <th scope="col">nombre </th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    for (Lieu lieu : lieus){ %>
                                <tr>
                                    <th scope="row"><%=lieu.getIdLieu()%></th>
                                    <td><%=lieu.getNomLieu()%></td>
                                    <td><%=lieu.getNomType()%></td>
                                    <td><%=lieu.getNombre()%></td>
                                </tr>
                                <% }%>
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
                            <form action="${pageContext.request.contextPath}/validerModifCreation" method="post" autocomplete="off">
                                <div class="mb-3">
                                    <label for="n-2" class="form-label">Date du spectacle</label>
                                    <input type="date" class="form-control" name="daty" id="n-2" aria-describedby="emailHelp">
                                </div>
                                <div class="mb-3">
                                    <label for="n-02" class="form-label">heure debut</label>
                                    <input type="number" step="any" class="form-control" name="dure" id="n-02" aria-describedby="emailHelp">
                                </div>
                                <div class="mb-3">
                                    <label for="n-01" class="form-label">Lieu</label>
                                    <select id="n-01" name="lieu" class="form-select" aria-label="Default select example">
                                        <%
                                            for(Lieu lieu : lieus){ %>
                                        <option value="<%=lieu.getIdLieu()%>"><%=lieu.getNomLieu()%></option>
                                        <%  } %>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="exampleInputEmail1" class="form-label">Location</label>
                                    <input type="number" step="any" class="form-control" name="location" id="exampleInputEmail1" aria-describedby="emailHelp">
                                </div>
                                <div class="row">
                                    <div class="col-6">
                                        <button type="submit" class="btn btn-primary" style="width: 100%">Valider</button>
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

<%@ include file="Complement/Lien.jsp"%>
<%@ include file="Complement/Footer.jsp"%>
</body>
</html>
