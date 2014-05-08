<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>


<html>

<head>  
	
    <title>Dashboard</title>   
     
    <style>

    </style>
        
    <script type="text/javascript">
	    
    jQuery(document).ready(function(){
    	

    });
        
    </script>
    
</head>
<body>
	
	
	
	<h1>About Foundation</h1>
	
	<p>
	This is a basic web app project that ties together many best practice developed over time.  It aims to get people up an developing very quickly and to give them
	the flexibility to create they app they need.
	</p>
	
	<h2>Access Request</h2>
	To request access to the app, send an email <a href="mailto:${accessContact }"><span class="glyphicon glyphicon-envelope" style="margin-right:3px;"></span>here</a>.
	
	<h2>Problems?</h2>
	<p>For Proteomics problems, contact the <a target="_new" href="http://helpdesk.mayo.edu/TicketSubmission.aspx">Help Desk</a> (4-5500) and reference the 'LPEA Small Custom Applications' team.</p>
	
	<h2>Feedback is Valuable!</h2>
	<p>Let the <a href="mailto:${feedbackEmailAddress }"><span class="glyphicon glyphicon-envelope"  style="margin-right:3px;"></span>Proteomics development team</a> know what improvements can be made to make your life better!</p>
	
	<H2>Application Info</H2>
	<div class="container_12">
	<div class="row">
		<div class="col-md-2 justright">
		Version:
		</div> 
		<div class="col-md-4">
		${info.version }
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-2 justright">
		Running since:
		</div> 
		<div class="col-md-4">
		<joda:format value="${info.startTime }" style="SM"/>
		</div>
	</div>
	
	</div>
</body>
</html>