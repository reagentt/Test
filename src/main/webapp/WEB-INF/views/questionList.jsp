<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="pageContext.request.contextPath" />
<%@ page session="false" %>
<%@page pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Question List</title>
<script>
function confirmDeletion() {
	var ans = confirm('Do you really want to delete this question?');
	return ans;
}
</script>
</head>
<body>
[<a href="questionList.html">Manage List</a>] [<a href="performPoll.html">Perform List</a>]
<br/>
<hr></hr>

<table width="60%" border="1">
<tr>
<td align="center"><h3>Question</h3></td><td align="center"><h3>Action</h3></td>
</tr>
<c:forEach items="${questions}" var="question">
<tr>
	<td width="70%"><c:out value="${question.content}"/></td>
	<td align="center"><a href="editQuestion.html?id=<c:out value="${question.id}"/>"><button>Edit</button></a>
	&nbsp;&nbsp;<a href="deleteQuestion.html?id=<c:out value="${question.id}"/>" onclick="return confirmDeletion();"><button>Delete</button></a>
	</td>
</tr>
</c:forEach>
</table>
<br>
<a href="createQuestion.html">Add new question</a>
</body>
</html>