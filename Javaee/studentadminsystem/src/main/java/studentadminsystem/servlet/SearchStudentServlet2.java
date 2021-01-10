package studentadminsystem.servlet;

import com.google.gson.Gson;
import studentadminsystem.Dao.impl.StudentDaoImpl;
import studentadminsystem.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/searchStudentServlet2")
public class SearchStudentServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,获取学生数据
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String stuname=request.getParameter("userName");
        StudentDaoImpl studentDao = new StudentDaoImpl();
        List<Student> student= studentDao.getStudentByName(stuname);
        //2,把学生数据保存到request
        //去耦，服务器把数据存放到request，不关心客户端如何处理
        request.setAttribute("students",student);
        PrintWriter writer = response.getWriter();
        writer.println(new Gson().toJson(student));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
