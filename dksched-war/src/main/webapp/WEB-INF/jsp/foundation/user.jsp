<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>


<html lang="en">

<head>
  <title>User Admin</title>

  <style>

  </style>

  <script type="text/javascript" src="${pageContext.request.contextPath }/js/foundation-services.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath }/js/foundation-user.js"></script>

</head>

<body>

<div  ng-app="userManagementApp">
  

<div class='container' ng-controller="UserListCtrl">
  <h1>User Administration</h1>

  <div class="col-sm-6">

  <input type="text" placeholder="Search" ng-model="filterString"/>
    <a href='#addUser' id='addUser' class='btn btn-primary' ng-click="addUser()">Add User</a>

    <table class="table table-hover">
      <thead>
      <tr>
        <th>Lastname</th>
        <th>Firstname</th>
        <th>Lan ID</th>
        <th>Active?</th>
      </tr>
      </thead>
      <tbody>
        <tr ng-repeat="user in users | filter:filterString" ng-click="displayUser(user)">
          <td>{{user.lastname}}</td>
          <td>{{user.firstname}}</td>
          <td>{{user.lanId}}</td>
          <td>{{user.active}}</td>
        </tr>
      </tbody>
    </table>

  </div>
  <div class="col-sm-6 ng-hide" ng-show="user">
    <form role="form" name="form" novalidate>

      <div class="form-group row" ng-class="{'has-error': form.lanId.$error.required}">
        <label class="control-label col-sm-2 text-right" for="lanId">
          Lan ID
        </label>
        <div class="col-sm-4">
          <input type="text" class="form-control" id="lanId" name="lanId" placeholder="Lan ID" ng-model="user.lanId" required="required" ng-disabled="!user.canEditLanId()"/>
          <span class="help-block" ng-show="form.lanId.$error.required">Required</span>
        </div>
        
      </div>

      <div class="form-group row">
        <label for="lastname" class="control-label col-sm-2 text-right">Last</label>
        <div class="col-sm-4">
          <input type="text" class="form-control" id="lastname" placeholder="Last name" ng-model="user.lastname" required="required"/>
        </div>
      </div>

      <div class="form-group row">
        <label for="firstname" class="col-sm-2 text-right">First</label>
        <div class="col-sm-4">
          <input type="text" class="form-control" id="firstname" ng-model="user.firstname" required="required"/>
        </div>
      </div>

      <div class="form-group row">
        <label for="emailAddress" class="col-sm-2 text-right">eMail</label>
        <div class="col-sm-4">
          <input type="text" class="form-control" id="emailAddress" ng-model="user.emailAddress" required="required"/>
        </div>
      </div>

      <div class="form-group row">
        <label for="active" class="control-label col-sm-2 text-right">Active</label>
        <div class="col-sm-4">
          <input type="checkbox" class="form-control" id="active" ng-model="user.active"/>
        </div>
      </div>

      <div class="form-group row">
        <label for="active" class="control-label col-sm-2 text-right">Roles</label>
        <div class="col-sm-4">
          <div class="row" ng-repeat="role in user.roles">
            <label><input class="form-control" type="checkbox" ng-model="role.active"/>{{role.title}}</label>
          </div>
        </div>
      </div>

      <div class="form-group row">
          <input type="submit" class="btn btn-default col-sm-offset-1 col-sm-2" ng-click="saveUser(user)" value="Save"/>
          <a href="#" class="col-sm-2" ng-click="voidUser()">Cancel</a>
      </div>
    </form>
    
  </div>

   {{user | json}}
</div>

</div>

</body>
</html>