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
<a href="questionList.html" class="button"/>Manage List</a>  <a href="performPoll.html" class="button"/>Perform List</a>
<style>
	a.button {
		position: relative;
		display: inline-block;
		font-size: 90%;
		font-weight: 700;
		color: rgb(209,209,217);
		text-decoration: none;
		text-shadow: 0 -1px 2px rgba(0,0,0,.2);
		padding: .5em 1em;
		outline: none;
		border-radius: 3px;
		background: linear-gradient(rgb(110,112,120), rgb(81,81,86)) rgb(110,112,120);
		box-shadow:
		0 1px rgba(255,255,255,.2) inset,
		0 3px 5px rgba(0,1,6,.5),
		0 0 1px 1px rgba(0,1,6,.2);
		transition: .2s ease-in-out;
	}
	a.button:hover:not(:active) {
		background: linear-gradient(rgb(126,126,134), rgb(70,71,76)) rgb(126,126,134);
	}
	a.button:active {
		top: 1px;
		background: linear-gradient(rgb(76,77,82), rgb(56,57,62)) rgb(76,77,82);
		box-shadow:
		0 0 1px rgba(0,0,0,.5) inset,
		0 2px 3px rgba(0,0,0,.5) inset,
		0 1px 1px rgba(255,255,255,.1);
	}
</style>
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