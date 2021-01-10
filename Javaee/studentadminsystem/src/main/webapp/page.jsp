<%@ page import="studentadminsystem.util.PageUtil" %>
<link rel="stylesheet" href="../../css/ulstyle.css"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul style="width: 600px">
    <li style="width: 100px"><a href="liststudentsbypaging.jsp?page=1">首  页</a> </li>
    <%
        if(currentPage!= 1){
    %>
    <li style="width: 100px"><a href="liststudentsbypaging.jsp?page=<%=currentPage-1%>">上一页</a></li>
    <%
        }
    %>
    <%
        if(currentPage!=pageUtil.getMaxPage()){
    %>
    <li style="width: 100px"><a href="liststudentsbypaging.jsp?page=<%=currentPage+1%>">下一页</a></li>
    <%
        }
    %>
    <li style="width: 100px"><a href="liststudentsbypaging.jsp?page=<%=pageUtil.getMaxPage()%>">尾  页</a></li>
    <li style="width: 200px">

        <form action="liststudentsbypaging.jsp" method="post">
            <%--跳转到<input type="text" name="page" size="2">页--%>
            跳转到<select name="page" id="selectPage">
                <option>请选择</option>
            </select>页
            <input type="submit" name="submit" value="确定">
        </form>


    </li>

</ul>
<script>
    function insertPage(element,maxPage){
        for( i=1;i<maxPage;i++){
            element.options.add(new Option(i,i));
        }
    }
    insertPage(document.getElementById("selectPage"),"<%=pageUtil.getMaxPage()%>");
</script>
<%
    int cPage=pageUtil.getCurrentPage();
    if(cPage%4<4&&cPage%4!=0){
        int j=0;
        j=cPage/4;
        if(j==0){
            cPage=1;
        }else {
            cPage=j*4;
        }
        for (int i=cPage;i<cPage+4;i++){


%>
<a href="liststudentsbypaging.jsp?page=<%=i%>"><%=i%></a>
<%

    }
%>
<%
    }

    if(cPage%4==0){
    for (int i=cPage;i<cPage+4;i++){


%>
<a href="liststudentsbypaging.jsp?page=<%=i%>"><%=i%></a>
<%
    }
    }
%>