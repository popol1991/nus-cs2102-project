<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="container">
    <div id="header" class="container_16">
        <ul class="nav">
            <li><a href="/tutorial">Home</a></li>
            <li><a href="/tutorial/service/manage">My Restaurants</a></li>
            <li><a href="/tutorial/service/main/about">About</a></li>
            <li><a href="/tutorial/service/user/"><sec:authentication property="principal.username" /></a></li>
            <li><a href="/tutorial/service/account/logout">Logout</a></li>
        </ul>
    </div> 
</div>
