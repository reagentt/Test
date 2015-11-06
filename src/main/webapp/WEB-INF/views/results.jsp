<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Answer the question</title>
</head>
<body>
[<a href="questionList.html">Manage List</a>] [<a href="performPoll.html">Perform List</a>]
<br/>
<hr></hr>
<h3>Results:</h3>
Testing time: <c:out value="${poll.testingTime}"/><br><br>
Correct answers: <c:out value="${poll.correctAnswers}"/><br><br>
Incorrect answers: <c:out value="${poll.incorrectAnswers}"/><br><br>
Unanswered questions: <c:out value="${poll.unansweredQuestions}"/>
</body>
</html>