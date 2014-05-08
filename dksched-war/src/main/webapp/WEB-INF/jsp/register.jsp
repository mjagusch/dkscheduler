<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>  
    <title>Registration</title>
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

	<div class="container_12">
	
		<form class="form-horizontal" role="form" id='registerForm' name='f' action="${pageContext.request.contextPath}/" method="post">    
          <div class="form-group">
            <label class="col-sm-4 control-label text-right" for='name'>Enter a user name</label>
            <div class="col-sm-4">
              <input class="form-control" type='text' name='name' id='name'/>
            </div>
          </div>
          
          <div class="form-group">
            <label class="col-sm-4 control-label text-right" for='password'>Choose a password</label>
            <div class="col-sm-4">
              <input class="form-control" type='password' name='password'/>
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-4 control-label text-right" for='cpassword'>Confirm Password</label>
            <div class="col-sm-4">
              <input class="form-control" type='password' name='cpassword'/>
            </div>
          </div>
          
          <div class="form-group">
            <label class="col-sm-4 control-label text-right" for='email'>Enter your email address</label>
            <div class="col-sm-4">
              <input class="form-control" type='text' name='email' id='email'/>
            </div>
          </div>
          
          <div class="form-group">
            <button class="col-sm-offset-7 col-sm-1 btn btn-primary" type='submit' id='submitRegister' class='btn btn-primary'>Register</button>
          </div>  
		</form>
	</div>
</body>
</html>