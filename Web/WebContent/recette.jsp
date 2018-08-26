<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>		
<t:layout>
    <jsp:body>
    	<h1>Nouvelle Recette</h1> 
    	<c:if test="${not empty requestScope.erreurMsg}">
	    	<div class="alert alert-danger" role="alert">
			  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
			  <span class="sr-only">Error:</span>
  			  ${requestScope.erreurMsg}
			</div>		
	    </c:if>
    	<c:choose>
    	<c:when test="${listeBouteille.size() > 0}">
	    	<form name="formAjoutRecette" id="formAjoutRecette" method="post" action="recette" class="form-horizontal" enctype="multipart/form-data">
	    		<input type="hidden" name="action" value="ajouter">
	    		<div class="form-group">
				    <label for="txtNomRecette" class="col-sm-1 control-label" style="text-align:left">Nom</label>
				    <div class="col-sm-4">
				      <input class="form-control" type="text" name="txtNomRecette" id="txtNomRecette" placeholder="Nom de la recette">
				    </div>
  				</div>
  				<div class="form-group">
				    <label for="photo" class="col-sm-1 control-label" style="text-align:left">Photo</label>
				    <div class="col-sm-4">
				      <input class="file" type="file" name="photo" id="photo">
				    </div>
  				</div>
		    	<table id="tableRecette" class="table table-stripped">
		    		<thead>
		    			<tr>
		    				<th>Ingrédient</th>
		    				<th>Quantité</th>
		    				<th>Proportion %</th>
		    			</tr>
		    		</thead>
		    		<tbody>
		    			<c:forEach var="bouteille" items="${listeBouteille}" varStatus="status">	
			    			<tr class="selection">
			    				<td>
			    					<input type="checkbox" class="chkBouteille bigCheck" name="chkBouteille" value="${bouteille.id}">
			    					${bouteille.type}
			    				</td>
			    				<td>${bouteille.qte}</td>
			    				<td class="proportion"><input type="text" name="txtProportion_${bouteille.id}" id="txtProportion${bouteille.id}" class="form-control txtProportion"></td>
			    			</tr>
			    		</c:forEach>
		    		</tbody>
		    	</table>
		    	<input type="submit" class="btn btn-primary" name="btnAjoutRecette" id="btnAjoutRecette" value="Ajouter">
		    	<a id="btnAnnuler" class="btn btn-primary" href="${pageContext.request.contextPath}/recette?action=liste" >Annuler</a>
		    	<div class="alert alert-danger errorTxt" style="display:none;margin-top:20px" role="alert"></div>		    	
	    	</form>
    	</c:when>
    	<c:otherwise>
    		<div class="alert alert-danger" role="alert">
			  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
			  <span class="sr-only">Error:</span>
  			  Aucune bouteille configurée. Veillez insérer des bouteilles.
			</div>
    	</c:otherwise>
    	</c:choose>
    </jsp:body>
</t:layout>