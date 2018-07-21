<%--page - Directive governs general properties of the JSP--%>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page autoFlush="false" buffer="8kb" %>
<%@ page isThreadSafe="false" %>
<%@page isErrorPage="false" %>
<%@page errorPage="../error-page.jsp" %>
<%@page session="false" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Clock</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
</head>
<body class="bg-secondary">


<!--Clock module -->
<main role="main">
    <div class="container d-flex justify-content-center">
        <div class="card border-info m-3 bg-dark text-info shadow">
            <div class="card-header bg-transparent border-info p-0">
                <h1 class="border border-info bg-info text-light shadow p-3 text-center">
                    <%=LocalDate.now().getDayOfWeek()%>
                </h1>
            </div>
            <div class="card-body text-success text-center  p-0">

                <%
                    Date now = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    String formatedTime = sdf.format(now);
                    out.println("<h2 class=\"border border-info bg-info text-light shadow p-3\">" + formatedTime + "</h2>");
                %>
            </div>
            <div class="card-footer bg-transparent border-info text-center p-0">
                <h5 class="border border-info bg-info text-light shadow p-3 m-0">
                    <%=LocalDate.now()%>
                </h5>
            </div>
        </div>
    </div>
</main>
<jsp:include page="../main-js.jsp"></jsp:include>

</body>
<html>