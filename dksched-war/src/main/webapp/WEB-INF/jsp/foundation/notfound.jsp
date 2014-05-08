<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>


<html>

<head>  
	
    <title>Not Found</title>   
     
    <style>

    </style>
        
    <script type="text/javascript">
	    
        jQuery(function(){

        });
        
    </script>
    <script type="text/x-handlebars-template" id="clearanceRowTemplate">
		
	</script>
    
</head>
<body>

<div class='container_12'>
	<h1>Oops...</h1>
	<div class="row">The page you requested was not found. If you continue to have problems, please contact the help desk (4-5500)</div>
	<div class="row text-info spacer">${errorMessage}</div>
	
	<div class="row spacer"><a class="btn btn-primary" href="${pageContext.request.contextPath }/dashboard">Proteomics Dashboard</a></div>
	
</div>
</body>
</html>