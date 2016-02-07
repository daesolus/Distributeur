<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>		
<t:interface>
    <jsp:body>
    	<div id="interface" class="container-fluid">   	
    		
    		<form name="changeInterface" id="changeInterface" action="commande" method="post">
    			<h1>Commander<a id="btnAffichage" class="btn btnGlyphicon"><span class="glyphicon glyphicon-resize-small" aria-hidden="true"></span></a></h1>
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
			    	<form name="formCommandeSimple" id="formCommande" action="commande" method="post" class="form-horizontal" role="form">
			    		<input type="hidden" name="erreurMsg" value="${requestScope.erreurMsg}">
			    		<div id="choixRecette">
				    		<input type="hidden" name="erreurMsg" value="${requestScope.erreurMsg}">
				    		<div class="row">
			    			<c:forEach var="recette" items="${listeRecette}" varStatus="status">
			    				<div class="col-sm-6 col-md-4">
				    				<div class="thumbnail">
					    				<a href="#" class="btnChoixRecette" >
					    				<input type="hidden" name="hidId" class="hidId" value="${recette.id}" >
					    				<c:choose>
					    					<c:when test="${not empty recette.photo}">
					    						<img  src="${pageContext.request.contextPath}/img/upload/${recette.photo}"  />
					    					</c:when>
					    					<c:otherwise>
					    						<img  src="${pageContext.request.contextPath}/img/upload/catmeme.jpg"  />
					    					</c:otherwise>
					    				</c:choose>
				    					</a>
									     <div class="caption">
									       <h4>${recette.nom}</h4>
									    </div>
								   </div>
							   </div>
				    		</c:forEach>
				    		</div>
				    	</div>
				    	<input type="hidden" name="hidCommande" id="hidCommande" value="" >
			    		<div id="choixVerre" style="display:none">
			    			<a id="btnRetourCommande" class="btn btnGlyphicon"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span></a>
			    			<div class="row" id="headerImg">
			    				<div class="col-xs-6 col-md-4">
				    				<div class="thumbnail">
					    				<a class="btnChoixQuantite" >
					    					<input type="hidden" name="hidQte" class="hidQte" value="2" >
					    					<img  src="${pageContext.request.contextPath}/img/redcup_10.jpg">
				    					</a>
									     <div class="caption">
									       <h4>Verre 2oz</h4>
									    </div>
								   </div>
							   </div>
							   <div class="col-xs-6 col-md-4">
				    				<div class="thumbnail">
					    				<a class="btnChoixQuantite" >
					    					<input type="hidden" name="hidQte" class="hidQte" value="4" >
					    					<img  src="${pageContext.request.contextPath}/img/redcup_16.jpg">
				    					</a>
									     <div class="caption">
									       <h4>Pinte 4oz</h4>
									    </div>
								   </div>
							   </div>
							   <div class="col-xs-6 col-md-4">
				    				<div class="thumbnail">
					    				<a class="btnChoixQuantite" >
					    					<input type="hidden" name="hidQte" class="hidQte" value="6" >
					    					<img   src="${pageContext.request.contextPath}/img/redcup.jpg">
				    					</a>
									     <div class="caption">
									       <h4>Pinte impériale 6oz</h4>
									    </div>
								   </div>
							   </div>
						   </div>
						   <input type="submit" class="form-control btn btn-primary" style="height:75px" id="submitCommande"value="Commander" >
						   <input type="hidden" name="hidQuantite" id="hidQuantite" value="" >
						   <input type="hidden" name="action" value="commander">
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
    	</div>
    </jsp:body>
</t:interface>