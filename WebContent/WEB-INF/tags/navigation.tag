 <%@tag description="Navigation" pageEncoding="UTF-8"%>
 <div id="filler" class="fillerOff">
		    	<div id="sectionDetail">
		  			
		  		</div>
		</div>
  	<nav id="navigation" class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="${pageContext.request.contextPath}">Distributeur</a>
        </div>
        <div id="navbar" class="navbar-default navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath}/index.jsp">Accueil</a></li>
            <li><a href="${pageContext.request.contextPath}/bouteille">Bouteilles</a></li>
            <li><a href="${pageContext.request.contextPath}/recette?action=liste">Recettes</a></li>
            <li><a href="${pageContext.request.contextPath}/commande">Commande</a></li>
            <li><a href="${pageContext.request.contextPath}/propos.jsp">À propos</a></li>
          </ul>
        </div>
      </div>
    </nav>