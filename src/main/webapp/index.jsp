<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
   <form action="${pageContext.request.contextPath }/admin/selectUserByLogin"  method="post">
      <input name="username" type="text">
      <input name = "password" type="password">
      <input name="role" type="0">
      <input type="submit" value="登录">
   </form>
</body>
</html>