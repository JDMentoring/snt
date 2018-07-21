<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<jsp:useBean id="weekSche" class="bigpic.bean.WeekSchedule"/>
<jsp:setProperty name="weekSche" property="userName" value="NazarMh"/>

<head>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

    <title>Week Schedule</title>
</head>


<body class="bg-secondary">

<main role="main">
    <div class="container d-flex justify-content-center">
        <div class="card border-info m-3 bg-dark text-info shadow">
            <div class="card-header bg-transparent border-info p-0">
                <h1 class="border border-info bg-info text-light shadow p-3 text-center">
                    ${weekSche.title}
                </h1>
            </div>
            <div class="card-body text-success text-center  p-0">
                <h3 class="border border-info bg-info text-light shadow p-3">
                    There are <jsp:getProperty name="weekSche" property="workingDayNumbers"/> days in the week
                </h3>

                <h3 class="border border-info bg-info text-light shadow p-3">
                    <jsp:getProperty name="weekSche" property="userName"/> works on
                    <c:forEach var="day" items="${weekSche.workingDays}">
                        ${day},
                    </c:forEach>
                </h3>

                <h3 class="border border-info bg-info text-light shadow p-3">
                    Leaving ${weekSche.numbersDay - weekSche.workingDayNumbers} free days for rest
                </h3>

                <c:set var="freeDays" scope="session" value="${weekSche.numbersDay - weekSche.workingDayNumbers}"/>

                <h3 class="border border-info bg-info text-light shadow p-3">
                    That means ${weekSche.userName} is working ${100*weekSche.workingDayNumbers/weekSche.numbersDay}% of the time
                </h3>

                <h3 class="border border-info bg-info text-light shadow p-3">
                    <c:if test="${weekSche.workingDayNumbers > freeDays}">
                        This is far from ideal life :(
                    </c:if>
                </h3>
            </div>
        </div>
    </div>
</main>
<jsp:include page="../main-js.jsp"/>

</body>
<html>
