<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>		
<t:layout>
    <jsp:body>
    	<h1>Liste des recettes<a href="${pageContext.request.contextPath}/recette" class="btn btnGlyphicon"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></a></h1>
    	<form name="listeRecette" id="listeRecette" method="post" action="recette" class="form-horizontal">
    	<input type="hidden" name="action" id="hidAction">
    	<input type="hidden" name="hidRecette" id="hidRecette">
    	<c:if test="${not empty requestScope.erreurMsg}">
	    	<div class="alert alert-danger" role="alert">
			  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
			  <span class="sr-only">Error:</span>
  			  ${requestScope.erreurMsg}
			</div>
	    </c:if>
    	<table class="table table-stripped">
    		<thead>
	    		<tr>
	    			<th>Nom</th>
	    			<th>Photo</th>
	    			<th>Action</th>
	    		</tr>
    		</thead>
    		<tbody>
    			<c:forEach var="recette" items="${listeRecette}" varStatus="status">
	    			<tr>	
	    				<td>${recette.nom}</td>
	    				<td>
	    					<div class="imageListeRecette">
	    						<img src="${pageContext.request.contextPath}/img/upload/${recette.photo}">
	    					</div>
	    				</td>
	    				<td>
	    					<input type="hidden" class="hidIdRecette" name="hidIdRecette" value="${recette.id}">
	    					<a href="${requestScope['javax.servlet.forward.request_uri']}?action=modifier&id=${recette.id}" class="btn btnGlyphicon">
	    						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
	    					</a>
	    					<a class="btn btnGlyphicon enleverRecette">
	    						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
	    					</a>
	    				</td>
	    			</tr>
    			</c:forEach>
    		</tbody>
    	</table>
    	</form>
    </jsp:body>
</t:layout>