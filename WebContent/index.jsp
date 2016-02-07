<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>		
<t:layout>
    <jsp:body>
    	<div id="index">
	    	<h1>Machine Distributrice</h1>
			<div class="row">
			  <div class="col-md-4 indexRow">
			  	<p>Besoin d'un brevage rapide ? D'un moment de détente absolue en buvant un brevage sans se casser la tête ? Le distributeur est à votre service !</p>
			  	<a class="btn btn-primary" href="${pageContext.request.contextPath}/commande">Le distributeur</a>
			  </div>
			  <div class="col-md-4 indexRow">
			  	<p>Vous avez de l'expérience ? Vous êtes prêt à faire un breuvage absolument magique ou atrocement dégeulasse ? La section recette est à votre service !</p>
			  	<a class="btn btn-primary" href="${pageContext.request.contextPath}/recette?action=liste">Les recettes</a>
			  </div>
			  <div class="col-md-4 indexRow">
			  	<p>Vous adorez ce merveilleux projet ? Ce distributeur n'aurait jamais pu se faire sans le courage et le talent de ces cinq étudiants en Génie Informatique. </p>
			  	<a class="btn btn-primary" href="${pageContext.request.contextPath}/propos.jsp">L'équipe</a>
			  </div>
			</div>			
		</div>
    </jsp:body>
</t:layout>