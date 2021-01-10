<%@ page import="studentadminsystem.model.Student" %>
<%@ page import="studentadminsystem.Dao.StudentDao" %>
<%@ page import="studentadminsystem.Dao.impl.StudentDaoImpl" %><%--
  Created by IntelliJ IDEA.
  User: 26615
  Date: 2020/10/27
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改学生页面</title>
</head>
<body>
<h1>修改学生信息</h1>

<%
    request.setCharacterEncoding("UTF-8");
    String stuno = request.getParameter("stuno");

    Student student=null;
    //StudentDao sdao=new StudentDaoImpl();
    System.out.println(stuno);
    if(stuno==null){
        out.print("<script>alert('未获取到学生信息,请先选择需要修改的学生');location.href='liststudents.jsp'</script>");
    }
    else {
        student=new StudentDaoImpl().getStudentByNo(stuno);
    }
   // Student student=null;
    //Student student= (Student) request.getAttribute("student");
%>
<form action="updataStudent" method="post">
请输入学号：<input type="text" id="stuno" name="stuno" value="<%=student.getStuNo()%>" disabled><br>
请输入姓名：<input type="text" id="stuname" name="stuname" value="<%=student.getStuName()%>"><br>
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
请输入电话：<input type="tel" id="tel" name="tel"><br>
请输入宿舍：<select id="dormno" name="dormno">
    <option value="3420">3-420</option>
    <option value="3419">3-419</option>
    <option value="10410">10-410</option>
    <option value="10408">10-408</option>
    <option value="10409">10-409</option>
    <option value="10411">10-411</option>
    <option value="3719">3-719</option>
    <option value="10130">10-130</option>
    <option value="10616">10-616</option>
    <option value="20617">20-617</option>
    <option value="10532">10-532</option>
    <option value="10425">10-425</option>
</select>
<br>
<input type="submit" value="添加" name="submit" id="submit">
</form>
<script>
    function addSelected(ele,value){
        var options=ele.options;
        for(option of options){
            //console.log(option.value);
            if(option.value==value){
                option.selected=true;
            }
        }
    }
    var classes=document.getElementById("classes");
    addSelected(classes,"<%=student.getClasses()%>");

    var department=document.getElementById("department");
    addSelected(department,"<%=student.getDepartment()%>");

    var gender=document.getElementById("gender");
    addSelected(gender,"<%=student.getGender()%>");

    var dormno=document.getElementById("dormno");
    addSelected(dormno,"<%=student.getDormNo()%>");

    var submit=document.getElementById("submit");

    var stuno=document.getElementById("stuno");
    submit.onclick=function (){
        stuno.disabled=false;
    }

</script>
<%

//    String stuname=request.getParameter("stuname");
//    String classes=request.getParameter("classes");
//    String gender=request.getParameter("gender");
//    String department=request.getParameter("department");
//    String tel=request.getParameter("tel");
//    String dormno=request.getParameter("dormno");
//    System.out.println(gender);
//    System.out.println(department);
//
//
//    Student s=new Student(stuno,stuname,classes,gender,department,tel,dormno,"0");
//    boolean result=true;
//    result=new StudentDaoImpl().updataStudent(s);
//    System.out.println(result);
//    if(result){
//        request.getRequestDispatcher("liststudents.jsp");
//                //.forward(request,response);
//    }
//    else {
//       //out.println("error");
//
//    }
%>



<%
/*
* 1.如何判断提交
* 2.由于 stuNo 为disabled 没法提交
* stuno 在整个页面都不会改变，因此可以直接使用
* 如果必须要提交可以使用js在提交时更改disabled 为flase
* 3.调用updataStudent(Student s)
* 4.根据updataStudent()返回结果给出提示
*   成功跳转到liststudent.jsp
*   失败跳转到error.jsp
* */
%>
</body>
</html>
