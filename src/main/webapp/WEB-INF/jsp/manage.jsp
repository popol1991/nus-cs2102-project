<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html class="home-page wf-minionpro1minionpro2-n4-active wf-minionpro1minionpro2-i4-active wf-active"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<head>
    <title>Food Empire</title>
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <link rel="stylesheet" href="<c:url value='/misc/stylesheets/reset.css'/>" type="text/css" />
    <link rel="stylesheet" href="<c:url value='/misc/stylesheets/font.css'/>" type="text/css" />
    <link rel="stylesheet" href="<c:url value='/misc/stylesheets/style.css'/>" type="text/css" />

    <script type="text/javascript" src="<c:url value='/misc/js/jquery-1.8.1.min.js'/>"></script>
    <script type="text/javascript">
    	function approve (restId) {
          $.ajax({
            url:"manage/approve/"+restId,
            success: function(message) {
              if (message == true) {
                alert("restaurant approved");
                $('button.restId'+restId).remove();
              } else {
                alert("approve failed!");
              }
            }
          });
      	};	
      function remove (restId) {
	      var confirm = window.confirm("Are you sure to delete this restaurant?");
	      if (confirm == true) {
	        $.ajax({
	          url:"manage/delete/"+restId,
	          success: function(message) {
	            if (message == true) {
	              alert("restaurant deleted");
	              $('li#'+restId).remove();
	            } else {
	              alert("delete failed!");
	            }
	          }
	        });
	      }
      };
      function modify (restId) {
        id = "#restId"+restId;
        var rest = $('div'+id);
        temp = rest.html();
        rest.slideUp();
        rest.html(getFormHtml(id,
                    $(id+" #name").text(),
                    $(id+" #address").text(),
                    $(id+" #category").text(),
                    $(id+" #avg").text(),
                    $(id+" #area").text()
                   ));
        rest.slideToggle();
      };
    </script>
</head>

<body>
<%@ include file="header.jsp" %>
<section class="working-with-us" id="working-with-us">
	<div class="container">
		<h1 class="section-title">Manage My Restaurants<span class="special">.</span></h1>
		<ul>
			<c:forEach var="rest" items="${restList}" varStatus="status">
	            <li id="${rest.id}">
	            	<div class="arrow"></div>
	            	<div class="fact">
	            		<h2>${rest.name}</h2>
	            		<p>
	            			<table width="300">
	            				<tr>
	            					<td width="130">Address:</td>
	            					<td>${rest.name}</td>
	            					<td><button id="small" onclick="modify(${rest.id})">modify</button></td>
	            				</tr>
	            				<tr>
	            					<td>Category:</td>
	            					<td>${rest.category}</td>
	            					<td><button id="small" onclick="remove(${rest.id})">delete</button></td>
	            				</tr>
	            				<tr>
	            					<td>Average Price:</td>
	            					<td>${rest.avgPrice}</td>
	            					<td>
	            						<c:if test="${rest.isApproved == 0}" >
	            							<sec:authorize access="hasRole('admin')">
	              								<button id="small" class="restId${rest.id}" onclick="approve(${rest.id})">approve</button>
	            							</sec:authorize>
	          							</c:if>
	            					</td>
	            				</tr>
	            				<tr>
	            					<td>Area:</td>
	            					<td>${rest.area}</td>
	            				</tr>
	            			</table>
	            		</p>
	            	</div>
	            </li>
   			</c:forEach> 
		</ul>
	</div>
</section>
<%@ include file="footer.jsp" %>
</body>
</html>