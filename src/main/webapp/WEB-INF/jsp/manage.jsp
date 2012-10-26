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
		<script type="text/javascript" src="<c:url value='/misc/js/general.js'/>"></script>
		<script type="text/javascript">
			var temp;
			function cancel () {
				var rest = $('div'+id);
				rest.slideUp();
				rest.slideToggle();
				rest.html(temp);
			};
			function submitModify (id) {
				$.get("manage/modify");
				var form = $('form'+id);
				$.post("manage/modify", 'id='+id.replace("#restId","")+'&'+form.serialize(), 
					function() {
						var rest = $('div'+id);
						rest.slideUp();
						rest.slideToggle();
						rest.html(getModifiedInfo(id,
												  $(id+' [name=name]').val(),
												  $(id+' [name=address]').val(),
												  $(id+' [name=category]').val(),
												  $(id+' [name=avgPrice]').val(),
												  $(id+' [name=area]').val()
												 ));
					}
				);
				return false;
			};	
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
								$('div#restId'+restId).remove();
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
			function addNew () {
				var div = $('<div class="whitewrapper"><div class="hd"></div></div>').append(getFormHtml()).appendTo($('.restaurantlist'));
				div.slideDown();
			}
		</script>
	</head>

<body>
	<%@ include file="header.jsp" %>
	<div class="wrapper container_16">
		<div id="services">
			<h1 class="aligncenter divider">My Restaurants</h1>
		</div>
		<div class="restaurantlist">
			<c:forEach var="rest" items="${restList}" varStatus="status">
	        	<div class="whitewrapper" id="restId${rest.id}">
					<div class="hd"></div> 
					<ul>
						<li>
							<label>Name</label>
							<span id='name'>${rest.name}</span>
						</li>
						<li>
							<label>Address</label>
							<span id='address'>${rest.address}</span>
						</li>
						<li>
							<label>Category</label>
							<span id='category'>${rest.category}</span>
						</li>
						<li>
							<label>Average Price</label>
							<span id='avg'>${rest.avgPrice}</span>
						</li>
						<li>
							<label>Area</label>
							<span id='area'>${rest.area}</span>
						</li>
					</ul>
					<button id="small" onclick="remove(${rest.id})">delete</button>
					<button id="small" onclick="modify(${rest.id})">modify</button>
					<c:if test="${rest.isApproved == 0}" >
						<sec:authorize access="hasRole('admin')">
							<button id="small" class="restId${rest.id}" onclick="approve(${rest.id})">approve</button>
						</sec:authorize>
					</c:if>
				</div>
			</c:forEach> 
		</div>
		<div class="aligncenter">
			<button id="big" onclick="addNew()">Add New</button>
		</div>
	</div>
</body>

</html>