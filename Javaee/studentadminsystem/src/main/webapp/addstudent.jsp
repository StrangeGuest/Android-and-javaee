<%@ page import="studentadminsystem.model.Student" %>
<%@ page import="studentadminsystem.Dao.impl.StudentDaoImpl" %>
<%@ page import="studentadminsystem.Dao.StudentDao" %><%--
  Created by IntelliJ IDEA.
  User: 26615
  Date: 2020/10/26
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加学生</title>
</head>
<body>
<h1>添加学生</h1><hr>

<form action="addStudent" method="post">
请输入学号：<input type="text" id="stuno" name="stuno"><br>
请输入姓名：<input type="text" id="stuname" name="stuname"><br>
请选择班级：<select id="classes" name="classes">
    <option value="1808041">1808041</option>
    <option value="1808042">1808042</option>
</select><br>
请选择性别：<select id="gender" name="gender">
    <option value="男">男</option>
    <option value="女">女</option>
</select><br>
请选择系部：<select id="department" name="department">
    <option value="软件工程">软件工程</option>
    <option value="计算机应用">计算机应用</option>
</select><br>
请输入电话；<input type="tel" id="tel" name="tel"><br>
请输入宿舍：<select id="dormno" name="dormno">
    <option value="3-420">3-420</option>
    <option value="10-410">10-410</option>
</select>
<br>

<input type="submit" value="添加" name="submit">
</form>

</body>
</html>
