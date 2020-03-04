<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix= "form" uri= "http://www.springframework.org/tags/form" %>
    <%@ taglib prefix= "spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试基于浏览器请求的国际化</title>
</head>
<body>
<!-- 用户可以选择切换语言环境 -->
<a href="loginForm?request_locale=zh_CN">中文</a> | <a href="loginForm?request_locale=en_US">英文</a>
<br/>
<!-- 使用message标签来输出国际化信息 -->
<h3><spring:message code="title" /></h3>
<form:form modelAttribute="user" method="post" action="login">
	<table>
		<tr>
			<td><spring:message code="loginname" /></td>
			<!-- form:input标签能将输入的值与path中的值组成键值对存入model中，传值到后台 -->
			<td><form:input path="loginname" /></td>
		</tr>
		<tr>
			<td><spring:message code="password" /></td>
			<td><form:input path="password" /></td>
		</tr>
		<tr><input type="submit" value="<spring:message code="submit" />" /></tr>
	</table>
</form:form>
</body>
</html>