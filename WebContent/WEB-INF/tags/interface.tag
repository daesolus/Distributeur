<%@tag description="Interface de commande" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>	
<!DOCTYPE html>
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
  	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
  	<script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
  	<script src="${pageContext.request.contextPath}/js/messages_fr.js"></script>
  	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
  	<script src="${pageContext.request.contextPath}/js/projet.js"></script>
  	<script src="${pageContext.request.contextPath}/js/projetValidation.js"></script>
  	
  	<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/img/favicon.png" />
  	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Équipe 12</title>
 </head>
 <body>
 	<t:navigation/>
 	<jsp:doBody />
 </body>
</html>