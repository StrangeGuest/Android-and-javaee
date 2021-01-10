package studentadminsystem.servlet;

import studentadminsystem.Dao.StudentDao;
import studentadminsystem.Dao.impl.StudentDaoImpl;
import studentadminsystem.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/searchStudent")
public class SearchStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String submit=request.getParameter("submit");
        StudentDao sdao=new StudentDaoImpl();
        List<Student> students=null;
        //List<Student> students=sdao.getAllStudent();
        if(submit!=null&&submit.equals("确定")){
            String name=request.getParameter("userName");
            students=sdao.getStudentByName(name);

        }else {
            try {
                students=sdao.getAllStudent();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("students",students);
        request.getRequestDispatcher("liststudents.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
