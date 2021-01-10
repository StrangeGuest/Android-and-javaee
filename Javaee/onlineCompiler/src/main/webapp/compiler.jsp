<%--
  Created by IntelliJ IDEA.
  User: 26615
  Date: 2020/12/25
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>在线编译</title>
</head>
<style>
    textarea{
        width: 500px;
        height:500px;

    }
</style>
<body>
<form action="mycompiler" method="post">
        <textarea id="content" name="content">
public class HelloWorld {
    public static void main(String []args){
        System.out.println("Hello World!");
        }
    }
        </textarea>
    <textarea>
        <%
            String s="";
            if(request.getAttribute("result")!=null){
                s=(String)request.getAttribute("result");
            }

        %>
<%=s%>

        </textarea>
    <input type="submit" id="compile" name="compile" value="编译">
</form>
</body>
</html>
