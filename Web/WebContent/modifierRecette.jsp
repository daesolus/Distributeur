<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>		
<t:layout>
    <jsp:body>
    	<form id="formModifierBouteille" method="post" action="recette" name="formModifierBouteille" enctype="multipart/form-data">
    		<input type="hidden" id="hidAction" name="action" value="modifier">
    		<input type="hidden" id="hidBouteilleRecette" name="bouteilleRecette">
    		<input type="hidden" name="recetteId" value="${recette.id}">
	    	<div class="form-group">
	          <h2>Nom de la recette</h2>
	          <input type="text" class="form-control reduceInput" id="txtNom" name="txtNom" value="${recette.nom}">
	        </div>
	        <div class="form-group">
	          <h2>Photo</h2>
	          <input class="file" type="file" name="photo" id="photo">
	          <input type="hidden" name="nomFichier" id="nomFichier" value="${recette.photo}">
	          <c:choose>
				<c:when test="${not empty recette.photo}">
					<div id="photoRecette">
						<img  class="img-responsive" src="${pageContext.request.contextPath}/img/upload/${recette.photo}"  />
					</div>
				</c:when>
				<c:otherwise>
					<div id="photoRecette">
						<img  class="img-responsive" src="${pageContext.request.contextPath}/img/upload/catmeme.jpg"  />
					</div>
				</c:otherwise>
			  </c:choose>
	        </div>  
	        <h2>Liste des ingrédients</h2>
	        <table id="tableRecette" class="table table-stripped">
		    		<thead>
		    			<tr>
		    				<th>Ingrédient</th>
		    				<th>Proportion %</th>
		    				<th>Action</th>
		    			</tr>
		    		</thead>
		    		<tbody>
		    			<c:forEach var="bouteille" items="${recette.listeBouteille}" varStatus="status">	
			    			<tr class="selection">
			    				<td>${bouteille.key.type}</td>
			    				<td class="proportion">
			    					<input type="text" class="form-control txtProportion" name="bouteillePercent" id="bouteillePercent" value="${bouteille.value}">
			    				</td>
			    				<td>
			    					<input type="hidden" class="hidIdBouteilleRecette" name="hidIdBouteilleRecette" value="${bouteille.key.id}">
			    					<a class="btn btnGlyphicon enleverBouteilleRecette">
	    								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
	    							</a>
			    				</td>
			    			</tr>
			    		</c:forEach>
		    	</tbody>
		    </table>
		    <div class="alert alert-danger errorTxt" style="display:none;margin-top:20px" role="alert">
		    </div>
		    <input type="submit" class="btn btn-primary" name="submit" value="Enregistrer">
		    <a id="btnAnnuler" class="btn btn-primary" href="${pageContext.request.contextPath}/recette?action=liste" >Annuler</a>
        </form>
    </jsp:body>
</t:layout>