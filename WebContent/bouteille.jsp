<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>		
<t:layout>
    <jsp:body>
    	<h1>Distributeur</h1>
    	<h2>Bouteilles en stock</h2>	
    	<div id="distributeurBouteille">
	    	<form name="ajoutBouteille" id="ajoutBouteille" method="post" action="bouteille" class="form-horizontal" role="form" id="bouteilleForm">
		    	<c:if test="${not empty requestScope.erreurMsg}">
			    	<div class="alert alert-danger" role="alert">
					  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
					  <span class="sr-only">Error:</span>
		  			  ${requestScope.erreurMsg}
					</div>
	    		</c:if>
		    	<table id ="tableDistributeur" class="table table-stripped">
		    		<thead>
		    			<tr>
		    				<th>Type</th>
		    				<th>Marque</th>
		    				<th>Numéro de pompe</th>
		    				<th>Qte</th>
		    				<th>Action</th>
		    			</tr>
		    		</thead>
		    		<tbody>
		    		<c:forEach var="bouteille" items="${listeBouteille}" varStatus="status">	
		    			<tr>
		    				<td class="valBouteille tdType">
		    					${bouteille.type}
		    				</td>
		    				<td class="valBouteille tdMarque">${bouteille.marque}</td>
		    				<td class="valBouteille tdPompe">${bouteille.numeroPompe}</td>
		    				<td class="valBouteille tdQte">${bouteille.qte}</td>
		    				<td class="tdAction">
		    					<input type="hidden" class="hidId" name="hidId" value="${bouteille.id}">
		    					<a class="btn btnGlyphicon modifierBouteille">
		    						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
		    					</a>
		    					<a class="btn btnGlyphicon enleverBouteille">
		    						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
		    					</a>	
		    				</td>
		    			</tr>
		    		</c:forEach>
		    		</tbody>
		    	</table>
		    	<a id="btnAjoutBouteille" class="btn btn-primary">Ajouter bouteille</a>
		    	<input type="button" class="btn btn-primary" id="btnSaveBouteille" value="Enregistrer" name="btnSaveBouteille">
		    	<a id="btnAnnuler" class="btn btn-primary" style="display:none" >Annuler</a>
		    	<input type="hidden" id="hidBouteille" name="hidBouteille">
		    	<input type="hidden" id="hidAction" name="hidAction">
		    	
		    	<p>${requestScope.error}</p>
		    </form>
    	</div> 
    </jsp:body>
</t:layout>
