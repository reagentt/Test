<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit question</title>
</head>
<body>
[<a href="questionList.html">Manage List</a>] [<a href="performPoll.html">Perform List</a>]
<br/>
<hr></hr>

<form:form action="editQuestion.html" commandName="question">
<table>
<tr><td>Question: </td>
<td>
<form:input size="25" path="content"/>
<form:input type="hidden" path="id"/>
</td></tr>
<tr><td></td><td></td></tr>
</table>
<br>
<table border="1" width="60%">
<tr><td width="70%" align="center">Answer</td><td align="center">Correct</td><td align="center">Delete</td></tr>
<c:forEach items="${question.answers}" var="answer">
<tr><td><c:out value="${answer.content}"/></td>
<td align="center"><input type="radio" name="answer_id" <c:if test="${answer.isRight eq 1}">checked</c:if> value="<c:out value="${answer.id}"/>"/></td>
<td align="center"><input type="checkbox" name="delete_answer" value="<c:out value="${answer.id}"/>"/></td>
</tr>
</c:forEach>
</table>
<br>
<a href="createAnswer.html?questionId=<c:out value="${question.id}"/>">Add Answer</a>
<br><br>
<input type="submit" value="Save"/>&nbsp;
<input type="button" onclick="location.href = '/questionList.html';return false;" value="cancel"/>
</form:form>
</body>
</html>