package studentadminsystem.servlet;

import studentadminsystem.Dao.impl.StudentDaoImpl;
import studentadminsystem.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String stuno=request.getParameter("stuno");
        String stuname=request.getParameter("stuname");
        String classes=request.getParameter("classes");
        String gender=request.getParameter("gender");
        String department=request.getParameter("department");
        String tel=request.getParameter("tel");
        String dormno=request.getParameter("dormno");
        String photopath="";
        if(gender.equals("男")){
            photopath="images/boy.jpeg";
        }else {
            photopath="images/girl.jpeg";

        }
        Student s=new Student(stuno,stuname,classes,gender,department,tel,dormno,photopath);

        boolean result=new StudentDaoImpl().saveStudent2(s);
        //System.out.println(result);
        if(result){
            System.out.println("添加成功");
            response.getWriter().write("<script>alert('添加成功');location.href=('liststudents.jsp');</script>");
            //request.getRequestDispatcher("liststudents.jsp").forward(request,response);
        }else {
            response.getWriter().write("<script>alert('添加失败');</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
