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
	<form:form action="performPoll.html" modelAttribute="pollForm" method="POST">
	Question: <c:out value="${pollForm.question.content}"/>
	<table>
	<c:forEach items="${pollForm.question.answers}" var="answer">
		<tr>
			<td><input type="radio" name="answer_id" value="<c:out value="${answer.id}"/>"/></td>
			<td><c:out value="${answer.content}"/></td>
		</tr>
	</c:forEach>	
	</table><br><br>
	<input type="submit" value="next"/>
	</form:form>
</body>
</html>