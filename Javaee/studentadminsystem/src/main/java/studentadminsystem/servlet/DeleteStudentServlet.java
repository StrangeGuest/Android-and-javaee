package studentadminsystem.servlet;

import studentadminsystem.Dao.impl.StudentDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String stuno = request.getParameter("stuno");
        boolean result= new StudentDaoImpl().deleteStudentByNo(stuno);
        if(result){
            System.out.println("删除成功");
            response.getWriter().write("<script>alert('删除成功');location.href=('liststudents.jsp');</script>");
           // request.getRequestDispatcher("liststudents.jsp").forward(request,response);

        }else {
            response.getWriter().write("<script>alert('删除失败');</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
