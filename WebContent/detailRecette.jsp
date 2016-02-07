<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="detail">
	<div id="detailHeader">	
		<p>Détails de la recette<a id="closeDetail" class="btn btnGlyphicon"><span  class="glyphicon glyphicon-remove" aria-hidden="true"></span></a></p>
	</div>
	<h4>Recette ${recette.nom}</h4>
	<ul>
		<c:forEach var="bouteille" items="${recette.listeBouteille}" varStatus="status">
			<li>${bouteille.value}% de ${bouteille.key.type} (pompe ${bouteille.key.numeroPompe})</li>
		</c:forEach>
	</ul>
	<div id="detailFooter"></div>
</div>
