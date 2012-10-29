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
    <script type="text/javascript" src="<c:url value='/misc/js/jquery.pajinate.min.js'/>"></script>
    <script type="text/javascript">
    	$(document).ready(function() {
    		$('#paging_container').pajinate({
    			num_page_links_to_display : 5,
    			items_per_page:6
    		});
    	});
    </script>
</head>

<body>
<%@ include file="header.jsp" %>
<section class="contact-us" id="contact-us">
	<div class="container">
		<h1 class="section-title">${rest.name}<span class="special">.</span></h1>
		<div class="column">
			<form id="contact-form" class="contact-form" name="contact-form" method="post" novalidate="" >
			<ul>
				<li>
					<img src="/tutorial/misc/image/restaurant/${rest.id}.jpeg" />
				</li>
				<li>
					<div class="label">
						<label for="address">Address</label>
					</div>
					<input type="text" name="address" id="address"  value="${rest.address}" readonly="readonly">
				</li>
				<li>
					<div class="label">
						<label for="category">Category</label>
					</div>
					<input type="text" name="category" id="category"  value="${rest.category}" readonly="readonly">
				</li>
				<li>
					<div class="label">
						<label for="avgPrice">Average Price</label>
					</div>
					<input type="text" name="avgPrice" id="avgPrice"  value="${rest.avgPrice}" readonly="readonly">
				</li>
				<li>
					<div class="label">
						<label for="area">Area</label>
					</div>
					<input type="text" name="area" id="area"  value="${rest.area}" readonly="readonly">
				</li>
				<li>
					<div class="label">
						<label for="area">Your Review</label>
					</div>
					<textarea name="review" id="review"></textarea>
				</li>
			</ul>
			<input type="submit" id="contact-submit" name="contact-submit" value="Submit">	
			</form>
		</div>
		<div class="column">
			<div class="contact-elsewhere" id="paging_container">
				<h2>Reviews</h2>
				<ul class="content">
					<li>
						<a href="" class="email">
							<span class="action">popol…</span>
							<span class="downsize">The Japanese cuisine in Singapore is not as good as in Dalian!</span>
						</a>
					</li>
				</ul>
				<div class="page_navigation"></div>
			</div>
		</div>
	</div>
</section>
<%@ include file="footer.jsp" %>
</body>
</html>