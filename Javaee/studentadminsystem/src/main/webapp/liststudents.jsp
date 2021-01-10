<%@ page import="studentadminsystem.Dao.StudentDao" %>
<%@ page import="studentadminsystem.Dao.impl.StudentDaoImpl" %>
<%@ page import="studentadminsystem.model.Student" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 26615
  Date: 2020/10/12
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>学生列表展示</title>

</head>
<link rel="stylesheet" href="bootstrap-4.5.3-dist/css/bootstrap.css"/>
<script src="js/jquery-3.5.1.min.js"></script>
<style>
    img{
        width: 100px;
        height: 100px;
    }
    td{
        text-align: center;
    }
</style>
<body>
<hr>
<form action="liststudents.jsp" method="post">
    请输入姓名：<input type="text" id="userName" name="userName">
    <input type="submit" value="确定" id="submit" name="submit">
</form>
<a href="addstudent.jsp">添加学生信息</a>
<hr>
<%

%>
<table border="1px solid black" id="students" class="table table-bordered table-striped">
    <tr>
        <td>学号</td>
        <td>姓名</td>
        <td>班级</td>
        <td>性别</td>
        <td>专业</td>
        <td>宿舍号</td>
        <td>电话</td>
        <td>照片</td>
        <td>操作</td>
    </tr>

<%
    request.setCharacterEncoding("utf-8");
    String submit=request.getParameter("submit");
    StudentDao sdao=new StudentDaoImpl();
    List<Student> students=null;
    //List<Student> students=sdao.getAllStudent();
    if(submit!=null&&submit.equals("确定")){
        String name=request.getParameter("userName");
        students=sdao.getStudentByName(name);

    }else {
        students=sdao.getAllStudent();
    }

    //List<Student> students=(List<Student>)request.getAttribute("students");
    for(Student s:students){


%>

    <tr>
        <td><%=s.getStuNo()%></td>
        <td><%=s.getStuName()%></td>
        <td><%=s.getClasses()%></td>
        <td><%=s.getGender()%></td>
        <td><%=s.getDepartment()%></td>
        <td><%=s.getDormNo()%></td>
        <td><%=s.getTel()%></td>
        <td><img src="<%=s.getPhotoPath()%>" ></td>
        <td><a href='deleteStudent?stuno=<%=s.getStuNo()%>'>删除</a>&nbsp;<a href="updatastudent.jsp?stuno=<%=s.getStuNo()%>">更新</a>
        </td>
    </tr>
<%
    }
%>
</table>
</body>
</html>
