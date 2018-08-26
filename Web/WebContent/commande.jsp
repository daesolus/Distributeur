<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>		
<t:layout>
    <jsp:body>
    	<form name="changeInterface" id="changeInterface" action="commande" method="post">
    		<h1>Les Recettes<a id="btnAffichage" class="btn btnGlyphicon"><span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span></a></h1>
    		<input type="hidden" name="formInter" value="changeInterface" />
    	</form>
    		<c:if test="${not empty requestScope.erreurMsg}">
		    	<div class="alert alert-danger" role="alert">
				  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
				  <span class="sr-only">Error:</span>
	  			  ${requestScope.erreurMsg}
				</div>
	    	</c:if>
    		<c:choose>
		    	<c:when test="${listeRecette.size() > 0}">
			    	<form name="formCommande" id="formCommande" action="commande" method="post" class="form-horizontal" role="form">
			    		<input type="hidden" name="erreurMsg" value="${requestScope.erreurMsg}">
				    	<table class="table table-stripped">
				    		<thead>
				    			<tr>
				    				<th>Nom recette</th>
				    				<th>Informations</th>
				    				<th>Qte (oz)</th>
				    			</tr>
				    		</thead>
				    		<tbody>
				    			<c:forEach var="recette" items="${listeRecette}" varStatus="status">
					    			<tr class="recetteRow selection">
					    				<td>
					    					<input class="chkCommande bigCheck" type="checkbox" name="selectRecette" value="${recette.id}">
					    					${recette.nom}
					    				</td>
					    				<td>	
					    					<a class="recetteDetail btn btnGlyphicon">
					    						<span style="color:black" class="glyphicon glyphicon-cog" aria-hidden="true"></span>
											</a>
					    					<input type="hidden" name="hidIdRecette" class="hidIdRecette" value="${recette.id}">		    					
					    				</td>
					    				<td>
					    					<input type="text" name="txtQte_${recette.id}" id="txtQte${recette.id}" class="form-control txtQte">
					    				</td>
					    			</tr>
					    		</c:forEach>
				    		</tbody>
				    	</table>
			    		<input type="submit" id="btnCommander" name="btnCommander" class="btn btn-primary" value="Commander">
			    		<div class="alert alert-danger errorTxt" style="display:none;margin-top:20px" role="alert">
		    			</div>
			    	</form>
			    </c:when>
		    	<c:otherwise>
		    		<div class="alert alert-danger" role="alert">
					  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
					  <span class="sr-only">Error:</span>
		  			  Aucune recette configurée. Veillez insérer des recettes.
					</div>
		    	</c:otherwise>
	    	</c:choose>
	  		<c:if test="${listeCommande.size() < 0}">
		    	<h1>En attente</h1>
		    	<table class="table table-stripped">
		    		<thead>
		    			<tr>
		    				<th>Commande</th>
		    				<th>Information</th>
		    			</tr>
		    		</thead>
		    		<tbody>
		    			<c:forEach var="commande" items="${listeCommande}" varStatus="status">
			    			<tr class="trRecette">
			    				<td>Recette</td>
			    				<td>${commande.date}</td>
			    			</tr>
			    			<c:forEach var="recetteCommande" items="${commande.listeRecette}" varStatus="status">
				    			<tr>
				    				<td>${recetteCommande.nom}</td>
				    				<td>${recetteCommande.nom}</td>
				    			</tr>
				    		</c:forEach>
			    		</c:forEach>
		    			
		    		</tbody>
		    	</table>
	    	</c:if> 	
    </jsp:body>
</t:layout>