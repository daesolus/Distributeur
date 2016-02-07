<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>		
<t:layout>
    <jsp:body>
	    <div id="propos">
	    <h1>Membres de l'équipe</h1>
			<div id="francis" class="wrapperPropos">
				<div class="photoMembre">
					<img src="${pageContext.request.contextPath}/img/membres/francis.jpg">
				</div>
				<h2>Francis Lussier </h2>
			</div>
			<div id="juteau">	
				<div class="photoMembre">
					<img src="${pageContext.request.contextPath}/img/membres/juteau.jpg">
				</div>
				<p>
					<h2>Jean-Michel Juteau</h2>
				</p>
			</div>
			<div id="dave">	
				<div class="photoMembre">
					<img src="${pageContext.request.contextPath}/img/membres/dave.jpg">
				</div>
				<p>
					<h2>David Corbeil Stroombergen </h2>
				</p>
			</div>
			<div id="boucher">	
				<div class="photoMembre">
					<img src="${pageContext.request.contextPath}/img/membres/boucher.jpg">
				</div>
				<p>
					<h2>Gabriel Boucher </h2>
				</p>
			</div>
			<div id="lemoine">	
				<div class="photoMembre">
					<img src="${pageContext.request.contextPath}/img/membres/lemoine.png">
				</div>
				<p>
					<h2>Christopher Lemonde </h2>
				</p>
			</div>
		</div>
    </jsp:body>
</t:layout>
