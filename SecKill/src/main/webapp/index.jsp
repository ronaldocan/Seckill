<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<h2>Hello World!</h2>
<form:form commandName="user" action="/valid/signup" method="post">
    用户名<form:input path="username"/><form:errors path="username"/>
</form:form>
</body>
</html>
