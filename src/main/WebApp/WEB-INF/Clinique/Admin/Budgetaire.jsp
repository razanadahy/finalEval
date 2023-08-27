<%@ page import="java.util.Vector" %>

<%@ page import="com.example.frontoffice.CliniqueModel.Budgetaire" %>
<%@ page import="com.example.frontoffice.CliniqueModel.BudgetJour2" %>
<%@ page import="com.example.frontoffice.CliniqueModel.ActeBudget" %>
<%@ page import="com.example.frontoffice.CliniqueModel.DepenseBudget" %>
<%@ page import="com.example.frontoffice.Function.Function" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    BudgetJour2 list= (BudgetJour2) request.getAttribute("budgetaire");
    double recetteRel=list.getRecette();
    double recetteBudget=list.getActBudget();
    double depenseRel= list.getDepense();
    double depenseBudget=list.getDeptBudget();
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
            <div class="col-12">
                <div class="card" style="border: none;">
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/admin/budgetaire" method="get" autocomplete="off">
                            <div class="row">
                                <div class="col-4">
                                    <select class="form-select" name="mois" id="inputGroupSelect01">
                                        <option value="1">Janvier</option>
                                        <option value="2">Fevrier</option>
                                        <option value="3">Mars</option>
                                        <option value="4">Avril</option>
                                        <option value="5">Mai</option>
                                        <option value="6">Juin</option>
                                        <option value="7">Juillet</option>
                                        <option value="8">Aout</option>
                                        <option value="9">Septembre</option>
                                        <option value="10">Octobre</option>
                                        <option value="11">Novembre</option>
                                        <option value="12">Decembre</option>
                                    </select>
                                </div>
                                <div class="col-4">
                                    <input type="number" class="form-control" id="n-202" name="anne"  aria-label="Default select example">
                                </div>

                                <div class="col-4">
                                    <button type="submit" class="btn btn-primary" style="width: 100%">rechercher</button>
                                </div>
                            </div>
                        </form>
                        <div class="text-center row">
                                <h3><%=request.getAttribute("date")%></h3>
                        </div>
                        <div class="row my-2">
                            <table class="table">
                                <h2 class="text-center text-success">Recette</h2>
                                <thead>
                                <tr>
                                    <th scope="col">Acte</th>
                                    <th scope="col">Reel</th>
                                    <th scope="col">Budget</th>
                                    <th scope="col">Realisation</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    for (ActeBudget p : list.getActes()){ %>
                                <tr>

                                    <td><%=p.getType()%></td>
                                    <td><%=Function.affichageDouble(p.getReel())%></td>
                                    <td><%=Function.affichageDouble(p.getBudget())%></td>
                                    <td><%=Function.arround(p.getPourcentage())%>%</td>
                                </tr>
                                <% } %>
                                <tr class="bg-info">
                                    <td></td>
                                    <td><%=Function.affichageDouble(recetteRel)%></td>
                                    <td><%=Function.affichageDouble(recetteBudget)%></td>
                                    <td><%=Function.arround(BudgetJour2.pourcentage(recetteRel,recetteBudget))%>%</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <hr>
                        <div class="row my-2">
                            <table class="table">
                                <h2 class="text-center text-danger">Depense</h2>
                                <thead>
                                <tr>
                                    <th scope="col">Depense</th>
                                    <th scope="col">Reel</th>
                                    <th scope="col">Budget</th>
                                    <th scope="col">Realisation</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    for (DepenseBudget p : list.getDepenses()){ %>
                                <tr>

                                    <td><%=p.getType()%></td>
                                    <td><%=Function.affichageDouble(p.getReel())%></td>
                                    <td><%=Function.affichageDouble(p.getBudget())%></td>
                                    <td><%=Function.arround(p.getPourcentage())%>%</td>
                                </tr>
                                <% } %>
                                <tr class="bg-info">
                                    <td>Total</td>
                                    <td><%=Function.affichageDouble(depenseRel)%></td>
                                    <td><%=Function.affichageDouble(depenseBudget)%></td>
                                    <td><%=Function.arround(BudgetJour2.pourcentage(depenseRel,depenseBudget))%>%</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <hr>
                        <div class="row my-2">
                            <table class="table">
                                <h2 class="text-center text-info">Benefice</h2>
                                <thead>
                                <tr>
                                    <th scope="col"></th>
                                    <th scope="col">Reel</th>
                                    <th scope="col">Budget</th>
                                    <th scope="col"></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>Recette</td>
                                    <td><%=Function.affichageDouble(recetteRel)%></td>
                                    <td><%=Function.affichageDouble(recetteBudget)%></td>
                                    <td><%=Function.arround(BudgetJour2.pourcentage(recetteRel,recetteBudget))%>%</td>
                                </tr>
                                <tr>
                                    <td>Depense</td>
                                    <td><%=Function.affichageDouble(depenseRel)%></td>
                                    <td><%=Function.affichageDouble(depenseBudget)%></td>
                                    <td><%=Function.arround(BudgetJour2.pourcentage(depenseRel,depenseBudget))%>%</td>
                                </tr>
                                <tr class="bg-info">
                                    <td></td>
                                    <td><%=Function.affichageDouble(recetteRel-depenseRel)%></td>
                                    <td><%=Function.affichageDouble(recetteBudget-depenseBudget)%></td>
                                    <td><%=Function.arround(BudgetJour2.pourcentage((recetteRel-depenseRel),(recetteBudget-depenseBudget)))%>%</td>
                                </tr>
                                </tbody>
                            </table>
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
