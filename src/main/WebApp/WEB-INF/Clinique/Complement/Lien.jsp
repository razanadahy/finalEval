<%--
  Created by IntelliJ IDEA.
  User: Andrianiavo
  Date: 16/05/2023
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="lien col-4 my-1" >
                        <div class="card rounded-3 text-center shadow-lg p-3 ma" style="background: #0a53be; max-height: 55px;">
                            <a href="${pageContext.request.contextPath}/admin/" style="position: relative; top: 50%; left: 50%; color: white; transform: translate(-50%,-50%); margin-top: 10px; font-size: 22px">
                                Patient
                            </a>
                        </div>
                    </div>
                    <div class="lien col-4 my-1" >
                        <div class="card rounded-3 text-center shadow-lg p-3 ma" style="background: #0a53be; max-height: 55px;">
                            <a href="${pageContext.request.contextPath}/admin/acte" style="position: relative; top: 50%; left: 50%; color: white; transform: translate(-50%,-50%); margin-top: 10px; font-size: 22px">
                                Acte
                            </a>
                        </div>
                    </div>
                    <div class="lien col-4 my-1" >
                        <div class="card rounded-3 text-center shadow-lg p-3 ma" style="background: #0a53be; max-height: 55px;">
                            <a href="${pageContext.request.contextPath}/admin/depense" style="position: relative; top: 50%; left: 50%; color: white; transform: translate(-50%,-50%); margin-top: 10px; font-size: 22px">
                                depense
                            </a>
                        </div>
                    </div>
                    <div class="lien col-4 my-1" >
                        <div class="card rounded-3 text-center shadow-lg p-3 ma" style="background: #0a53be; max-height: 55px;">
                            <a href="${pageContext.request.contextPath}/admin/budgetaire" style="position: relative; top: 50%; left: 50%; color: white; transform: translate(-50%,-50%); margin-top: 10px; font-size: 22px">
                                budgetaire
                            </a>
                        </div>
                    </div>
                    <div class="lien col-4 my-1" >
                        <div class="card rounded-3 text-center shadow-lg p-3 ma" style="background: #0a53be; max-height: 55px;">
                            <a href="${pageContext.request.contextPath}/utilisateur/" style="position: relative; top: 50%; left: 50%; color: white; transform: translate(-50%,-50%); margin-top: 10px; font-size: 22px">
                                Facture
                            </a>
                        </div>
                    </div>
                    <div class="lien col-4 my-1" >
                        <div class="card rounded-3 text-center shadow-lg p-3 ma" style="background: #0a53be; max-height: 55px;">
                            <a href="${pageContext.request.contextPath}/utilisateur/dept" style="position: relative; top: 50%; left: 50%; color: white; transform: translate(-50%,-50%); margin-top: 10px; font-size: 22px">
                                Saisir depense
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

