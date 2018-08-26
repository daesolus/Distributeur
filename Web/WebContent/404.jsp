<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>		
<t:layout>
    <jsp:body>
    	<div id="404">
    		<h1 id="question_img">
    			Strooomberlost
    			<img src="${pageContext.request.contextPath}/img/daveQuestion.png" />
    		</h1>
    		
    		<div id="erreur_img">
    			<img src="${pageContext.request.contextPath}/img/daveSalty.jpg" />
    		</div>
    		<a  href="javascript:history.back()" class="btn btn-primary btnRetour">Revenir</a>
    	</div>
    </jsp:body>
</t:layout>

