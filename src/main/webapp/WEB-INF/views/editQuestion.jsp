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