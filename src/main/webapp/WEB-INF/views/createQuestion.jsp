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
	<form:form action="TestQuestionForm.html" modelAttribute="questionForm" method="POST">
	<table>
		<tr>
			<td>Please enter your question:</td>
			<td><form:input path="question.content" /></td>
		</tr>
		<tr>
			<td><input type="submit"/></td>
		</tr>
	</table>
	</form:form>
</body>
</html>