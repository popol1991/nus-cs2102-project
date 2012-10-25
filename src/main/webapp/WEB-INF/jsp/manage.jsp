<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" class="wf-fftisawebpro1fftisawebpro2-n4-active wf-fftisawebpro1fftisawebpro2-i4-active wf-fftisawebpro1fftisawebpro2-n7-active wf-brandongrotesque1brandongrotesque2-n4-active wf-brandongrotesque1brandongrotesque2-n7-active wf-active">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="<c:url value='/misc/stylesheets/style.css'/>" type="text/css" />
		<link rel="stylesheet" href="<c:url value='/misc/stylesheets/960.css'/>" type="text/css" />
		<script type="text/javascript" src="<c:url value='/misc/js/jquery-1.8.1.min.js'/>"></script>
	</head>

<body>
	<%@ include file="header.jsp" %>
	<div class="wrapper container_16">
		<div id="services">
			<h1 class="aligncenter divider">My Restaurants</h1>
		</div>
		<div class="restaurantlist">
			<c:forEach var="rest" items="${restList}" varStatus="status"> 
	        	<div class="whitewrapper">
					<div class="hd"></div> 
					<p>${rest.name} ${rest.address} ${rest.category} ${rest.isApproved}</p>
					<a href=""><button id="small">delete</button></a>
					<a href=""><button id="small">modify</button></a>
					<c:if test="${rest.isApproved == 0}" >
						<sec:authorize access="hasRole('admin')">
							<a href=""><button id="small">approve</button></a>
						</sec:authorize>
					</c:if>
				</div>
			</c:forEach> 
		</div>
		<div class="aligncenter">
			<a href=""><button id="big">Add New</button></a>
		</div>
	</div>
</body>

</html>