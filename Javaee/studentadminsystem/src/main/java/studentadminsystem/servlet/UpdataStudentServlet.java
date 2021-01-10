package studentadminsystem.servlet;

import studentadminsystem.Dao.impl.StudentDaoImpl;
import studentadminsystem.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updataStudent")
public class UpdataStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String stuno = request.getParameter("stuno");
//        Student student=null;
//        //StudentDao sdao=new StudentDaoImpl();
//        System.out.println(stuno);
//        if(stuno==null){
//            response.getWriter().write("<script>alert('未获取到学生信息,请先选择需要修改的学生');location.href='liststudents.jsp'</script>");
//        }
//        else {
//            request.getRequestDispatcher("updatastudent.jsp").forward(request,response);
//            student=new StudentDaoImpl().getStudentByNo(stuno);
//            System.out.println(student);
//            request.setAttribute("student",student);
//        }

        String stuname=request.getParameter("stuname");
        String classes=request.getParameter("classes");
        String gender=request.getParameter("gender");
        String department=request.getParameter("department");
        String tel=request.getParameter("tel");
        String dormno=request.getParameter("dormno");

        Student s=new Student(stuno,stuname,classes,gender,department,tel,dormno,"0");
        boolean result=true;
        result=new StudentDaoImpl().updataStudent(s);
        System.out.println(result);
        if(result){
            request.getRequestDispatcher("liststudents.jsp").forward(request,response);

        }
        else {
            response.getWriter().write("error");

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
