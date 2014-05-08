<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>


<html>

<head>  
	<meta name="ngapp" content="ng-app='propertyApp'"/>
    <title>Properties</title>   
     
    <style>

    </style>
     
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/foundation-property.js"></script>

	<script type="text/javascript">
		
	$(function(){
		
		$('.editable').each(function(i,editable){
			var $editable=$(editable);
			var value={original:$editable.html()};
			var url=$editable.data('url');
			
			
			var $valuebox = $("<div style='float:left; width:30em;' class='editValue'></div>").text(value.original);
			
			var $editIcon = $("<div class='glyphicon glyphicon-edit' style='margin-left:2em;'></div>");
			var $saveIcon = $("<div>").addClass("glyphicon glyphicon-ok").hide();
			var $cancelIcon = $("<div>").addClass("glyphicon glyphicon-remove").hide();
			
			var $actionbox = $("<span class='editActions'></div>")
								.append($editIcon)
								.append($saveIcon)
								.append($cancelIcon);
			
			var $editInput = $("<input type='text' style='width:30em; float:left;'>").val(value.original).hide();
		
			$editable.empty()
				.append($valuebox)
				.append($editInput)
				.append($actionbox);
			
			$editIcon.click(function(){
				$saveIcon.click(function(){
					value.updated=$editInput.val();
					$.ajax({
						type:"post",
						url:url,
						dataType:"json",
						data:value,
						success: function(data, status, jqXHR){
							$valuebox.html(data.value);
							value.original = data.value;
							$editInput.val(data.value);
							$editInput.hide();
							$valuebox.insertBefore($actionbox);
							
							$editIcon.show();
							$saveIcon.hide();
							$cancelIcon.hide();
							$valuebox.show();
							
						},
						error: function(jqXHR, status, error){
							alert("There was an error setting the variable.  Refresh the page an try again.");
						}
					});
				});
				
				$cancelIcon.click(function(){
					$valuebox.hide();
					$editInput.hide();
					$valuebox.show();

					$editIcon.show();
					$saveIcon.hide();
					$cancelIcon.hide();
					$valuebox.show();
				});
				
				$editInput.show();
				$valuebox.hide();
				$editIcon.hide();
				$saveIcon.show();
				$cancelIcon.show();
			});
		});
	});
	</script>    
</head>
<body>

<div class='container_12'>
	<table id='propertyTable' class='table table-striped'>
		<thead>
			<tr>
				<th>Property</th>
				<th>Value</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach var="prop" items="${properties }">
			<tr id='property_${prop.key}'>
				<td title="${prop.description }">${prop.key}</td>
				<td class='editable' data-url='${pageContext.request.contextPath }/property/${prop.id}'>${prop.value}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

</body>
</html>