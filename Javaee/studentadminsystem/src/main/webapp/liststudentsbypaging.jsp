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
    <link href="../../css/student.css" type="text/css" rel="stylesheet">
</head>

<body>
<hr>
<form action="liststudents.jsp" method="post">
    请输入姓名：<input type="text" id="userName" name="userName">
    <input type="submit" value="确定" id="submit" name="submit">
</form>
<hr>
<%

%>
<table border="1px solid black">
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
        String page1=request.getParameter("page");
        //判断page是否存在？page1==null 设置page1=1,初始值设置为第一页
        int pageSize=5;
        long totalRecords=0;
        int currentPage=0;
        if (page1==null){
            page1="1";
        }
        currentPage=Integer.parseInt(page1);
        StudentDao sdao=new StudentDaoImpl();
        //如何优化总记录数
        Object totals= request.getAttribute("totals");
        if(totals==null){
            totalRecords=sdao.totalRecords();
            request.setAttribute("totals",totalRecords);
        }else{
            totalRecords=(long)totals;
        }
        //创建PageUtil对象
        PageUtil pageUtil = new PageUtil(totalRecords, pageSize, currentPage);
        List<Student> students=sdao.getStudentByPaging(pageUtil.getCurrentPage(),pageSize);
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
        <td>
            <a href="updatastudent.jsp?stuno=<%=s.getStuNo()%>">修改</a>
        </td>
    </tr>
    <%
        }
    %>
</table>
<hr>
<%--<jsp:include page="page.jsp"/>--%>
<%@include file="page.jsp"%>
</body>
</html>
