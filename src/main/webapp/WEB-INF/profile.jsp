<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <jsp:include page="/WEB-INF/partials/head.jsp">
            <jsp:param name="title" value="Your Profile" />
        </jsp:include>
        <style>
            #body{
                background-color: lightgray;
            }
            #main_header{
                text-align: center;
                color: blue;
            }
        </style>
    </head>
    <body id="body">
        <jsp:include page="/WEB-INF/partials/navbar.jsp" />

        <div class="container-fluid">
            <h1 id="main_header">Welcome, ${sessionScope.user.username}!</h1>
        </div>

    </body>
</html>
