<!DOCTYPE html>
<%@page import="org.autumnridge.disciplekids.util.ArcLogger" %>

<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang='en'>
<head>

  <META http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
  <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
  <META HTTP-EQUIV="Expires" CONTENT="-1">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <meta name="viewport" content="width=device-width">

  <title><decorator:title default="Application:"/> | Volunteer Scheduling</title>

  <link rel='stylesheet' type="text/css" charset="UTF-8"
        href="<%=request.getContextPath()%>/bootstrap3/css/bootstrap.css"/>
  <link rel='stylesheet' type="text/css" charset="UTF-8"
        href="<%=request.getContextPath()%>/pnotify/jquery.pnotify.default.css"/>
  <link rel='stylesheet' type="text/css" charset="UTF-8" href="<%=request.getContextPath()%>/css/app.css"/>
  <link rel='stylesheet' type="text/css" charset="UTF-8"
        href="<%=request.getContextPath()%>/font-awesome-4.0.3/css/font-awesome.min.css"/>

  <!-- [if lt IE 9]>
  <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->

  <script src='<%=request.getContextPath()%>/js/lib/respond.js'></script>
  <script src='<%=request.getContextPath()%>/js/lib/jquery-1.11.0.js'></script>
  <script src='<%=request.getContextPath()%>/js/lib/lodash.compat.min.js'></script>
  <script src='<%=request.getContextPath()%>/js/lib/underscore.string.js'></script>
  <script src='<%=request.getContextPath()%>/js/lib/moment.js'></script>
  <script src='<%=request.getContextPath()%>/js/lib/jquery.idletimeout.js'></script>
  <script src='<%=request.getContextPath()%>/js/lib/handlebars.js'></script>
  <script src='<%=request.getContextPath()%>/js/lib/json2.js'></script>

  <style type="text/css">
    body {
      background-color: ${THEME_BACKGROUND_COLOR};
    }

    #reauthentication {
      text-align: center;
      display: block;

      /*set the div in the top-left corner of the screen*/
      position: absolute;
      top: 0;
      left: 0;

      /*set the width and height to 100% of the screen*/
      width: 100%;
      height: 5000px;

      z-index: 999;
      background-color: black;
      color: white;
    }

    #reauthentication > #reauth-content {
      margin-left: 30%;
      text-align: left;
      margin-top: 10%;
    }

  </style>
  <decorator:head/>
</head>

<body>

<script type="text/javascript">

  _.mixin(_.str.exports()); //init underscore.string

//  <security:authorize ifAnyGranted="ROLE_ACTIVE">
//  <security:authentication property="principal" var="volunteer"/>
//  </security:authorize>
</script>

<div id="wrap">

  <div id='navbarheader' class="navbar navbar-inverse">
    <div class="navbar-header">
      <!-- button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button-->
      <a href="${pageContext.request.contextPath }/" class="navbar-brand" title="Go to the home page">
        <span class="glyphicon glyphicon-thumbs-up"></span>
        Volunteer Scheduling
      </a>
      <ul class="nav navbar-nav">
        <security:authorize ifAnyGranted="ROLE_ADMIN">
          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Manage <b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li><a href="#/manage/volunteers">Volunteers</a></li>
            <li><a href="#/manage/rooms">Rooms</a></li>
            <li><a href="#/manage/schedule">Schedule</a></li>
            <li class="divider"></li>
            <li><a href="#/manage/reports">Reports</a></li>
          </ul>
        </security:authorize>
      </ul>
    </div>

    <div class="collapse navbar-collapse">
      <ul class="nav navbar-nav navbar-right">
        <security:authorize ifAnyGranted="ROLE_ACTIVE">
          <li><a href="#">Welcome ${volunteer.name}</a></li>
        </security:authorize>
        <li><a href="${pageContext.request.contextPath}/about">About</a></li>
      </ul>      
    </div>
  </div>


  <decorator:body/>
  <div id="push"></div>

</div>


<div class="navbar-inverse" id="footer">
  <p class='navbar-text' style="margin-top:0px;">
    For Problems, contact <a target="_new" href="http://TODO.com"
                                            style="color:pink;">TODO</a>
  </p>

  <p class='navbar-text pull-right' style="font-size: smaller; margin-top:0px;">
    Copyright &copy; 2014 TODO
  </p>
</div>

<script src="${pageContext.request.contextPath}/js/lib/angular/angular.js"></script>
<script src="${pageContext.request.contextPath}/js/lib/angular/angular-resource.min.js"></script>
<script src="${pageContext.request.contextPath}/js/lib/angular/angular-route.min.js"></script>
<script src="${pageContext.request.contextPath}/js/lib/angular-moment.min.js"></script>
<script src="${pageContext.request.contextPath}/js/lib/checklist-model-0.1.3.js"></script>
<script src="${pageContext.request.contextPath}/js/lib/ui-utils.min.js"></script>
<script src="${pageContext.request.contextPath}/js/lib/ui-utils-ieshiv.min.js"></script>

<script src='<%=request.getContextPath()%>/bootstrap3/js/bootstrap.js'></script>
<script src='<%=request.getContextPath()%>/pnotify/jquery.pnotify.js'></script>

<script src='<%=request.getContextPath()%>/js/lib/ui-bootstrap-tpls-0.10.0.min.js'></script>
<script src='<%=request.getContextPath()%>/js/app.js'></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/main/dksched-services.js"></script>
<script src='<%=request.getContextPath()%>/js/main/MainController.js'></script>
<script src='<%=request.getContextPath()%>/js/main/VolunteerController.js'></script>
<script src='<%=request.getContextPath()%>/js/main/RoomController.js'></script>
<script src='<%=request.getContextPath()%>/js/main/ScheduleController.js'></script>
<script src='<%=request.getContextPath()%>/js/main/RecurringScheduleController.js'></script>
<script src='<%=request.getContextPath()%>/js/main/DateScheduleController.js'></script>
<script src='<%=request.getContextPath()%>/js/main/RoomScheduleController.js'></script>
<script src='<%=request.getContextPath()%>/js/main/VolunteerScheduleController.js'></script>

</body>
</html>
