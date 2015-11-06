<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create question</title>
</head>
<body>
[<a href="questionList.html">Manage List</a>] [<a href="performPoll.html">Perform List</a>]
<br/>
<hr></hr>
	<form:form action="createAnswer.html" modelAttribute="answerForm" method="POST">
	<table>
		<tr>
			<td>Answer:</td>
			<td><form:input path="answer.content" /></td>
		</tr>
		<tr>
			<td colspan="2" align="left">
			Is correct: <form:checkbox path="correct"/>
			<form:hidden path="questionId"/>
			</td>
		</tr>
		<tr>
			<td><input type="submit"/></td>
		</tr>
	</table>
	</form:form>
</body>
</html>