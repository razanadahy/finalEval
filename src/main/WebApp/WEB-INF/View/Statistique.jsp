<%@ page import="java.util.Vector" %>
<%@ page import="com.example.frontoffice.Model.*" %>
<%@ page import="com.example.frontoffice.Controller.MainController" %>
<%@ page import="org.jfree.chart.JFreeChart" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="org.jfree.chart.ChartUtilities" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.text.DecimalFormat" %>
<%
  Vector<Spectacle>spectacles= (Vector<Spectacle>) request.getAttribute("list");

  /*JFreeChart chart = MainController.createBarChart(spectacles);

  ByteArrayOutputStream chartImageStream = new ByteArrayOutputStream();
  ChartUtilities.writeChartAsPNG(chartImageStream, chart, 500, 300);

  byte[] chartImageBytes = chartImageStream.toByteArray();

  String chartImageBase64 = new String(Base64.getEncoder().encode(chartImageBytes));*/
  JFreeChart[] charts=new JFreeChart[spectacles.size()];
  String[]image=new String[spectacles.size()];
  for (int i = 0; i < spectacles.size() ; i++) {
    charts[i]= MainController.createBarChart(spectacles.elementAt(i));
    ByteArrayOutputStream chartImageStream = new ByteArrayOutputStream();
    ChartUtilities.writeChartAsPNG(chartImageStream, charts[i], 500, 300);
    byte[] chartImageBytes = chartImageStream.toByteArray();
    image[i]=new String(Base64.getEncoder().encode(chartImageBytes));
  }
  int size=0;
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
              <h2 class="text-center"><%=spect.getSpectacle()%></h2>
            </div>
              <%
                DecimalFormat decimalFormat = new DecimalFormat("#.00");
                double recete= 0;
                try {
                  recete = spect.getRealRecette();
                } catch (Exception e) {
                  e.printStackTrace();
                }
                double depense=spect.getTotal();
                double benefice=recete-depense;
                double taxe= 0;
                try {
                  taxe = Taxe.getTaxe();
                } catch (Exception e) {
                  e.printStackTrace();
                }
                double val=benefice*taxe;
                double benTax=benefice-benefice*taxe;
              %>
            <div class="row my-2">
              <div class="col-4">
                <h3>Recette: <span class="text-info"><%=decimalFormat.format(recete)%></span></h3>
              </div>
              <div class="col-4">
                <h3>Depense: <span class="text-info"><%=decimalFormat.format(depense)%></span></h3>
              </div>
              <div class="col-4">
                <h3>Benefice brut: <span class="text-info"><%=decimalFormat.format(benefice)%></span></h3>
              </div>
            </div>
            <hr>
            <div class="row my-2">
              <div class="col-6 ">
                <h3>Benefice net: <span class="text-info"><%=decimalFormat.format(benTax)%></span></h3>
              </div>
              <div class="col-6">
                <h3>Taxe: <span class="text-info"><%=decimalFormat.format(val)%></span></h3>
              </div>
            </div>
          </div>
          <div class="card-footer text-center">
              <img src="data:image/png;base64,<%= image[size] %>" alt="Bar Chart">
          </div>
          <div class="row my-4">
            <a href="${pageContext.request.contextPath}/export-pdf?id=<%=spect.getIdSpectacle()%>" type="button" class="btn btn-primary" >Exporter en PDF</a>
          </div>
          <%

             size+=1;
            }
          %>
        </div>
      </div>
    </div>
  </div>
</div>


<%@ include file="Complement/Lien.jsp"%>
<%@ include file="Complement/Footer.jsp"%>
</body>
</html>
