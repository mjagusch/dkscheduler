<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>  
    <title>Login</title>
    <style>
    
        h1{
            font-size:1.5em;            
        }
        
        h2{
            font-size:1.1em;
        }
        
        label, input{
            font-size:1.5em;
        }
        
        div#disclaimer{
            font-size: 0.8em;
            margin-top:3em;
        }
    </style>
    
    <!--  Detect if the user is using IE7 or earlier. If so, warn the user that their decision sucks! -->
    <script type="text/javascript">
	    <security:authorize ifAnyGranted="ROLE_ACTIVE">
   		window.location = '${pageContext.request.contextPath}/';
 		</security:authorize>
 		
        jQuery(document).ready(function(){
            jQuery("#j_username").focus();
        });
    </script>
        
</head>
<body>

	<div class="container">
	
		<form class="form-horizontal" role="form" id='loginForm' name='f' action="${pageContext.request.contextPath}/j_spring_security_check" method="post">    
	        <div class="form-group">
        		<label class="col-sm-2 control-label text-right" for='j_username'>User name</label>
        		<div class="col-sm-4">
              <input class="form-control" type='text' name='j_username' id='j_username'/>
            </div>
	        </div>
	        
	        <div class="form-group">
        		<label class="col-sm-2 control-label text-right" for='j_password'>Password</label>
        		<div class="col-sm-4">
              <input class="form-control" type='password' name='j_password'/>
            </div>
	        </div>
	        <div class="form-group">
	          <button class="col-sm-offset-5 col-sm-1 btn btn-primary" type='submit' id='submitLogin'>Login</button>
	        </div>	
          <div class="form-group">
            <span class="col-sm-offset-3">Not registered? <a href="register">Create a new account</a></span>
          </div>  
		</form>
	</div>
</body>
</html>