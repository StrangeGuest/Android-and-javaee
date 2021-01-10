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

@WebServlet("/ListStudentServlet2")
public class ListStudentServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //String submit=request.getParameter("submit");
        StudentDaoImpl sdao=new StudentDaoImpl();
        List<Student> allStudent=null;
        //List<Student> students=sdao.getAllStudent();
//        if(submit!=null&&submit.equals("确定")){
//            String name=request.getParameter("userName");
//            allStudent=sdao.getStudentByName(name);
//
//
//        }else {
//            allStudent=sdao.getAllStudent();
//
//        }
        allStudent=sdao.getAllStudent();










        //1.获取学生数据
//        StudentDaoImpl studentDao = new StudentDaoImpl();
//        List<Student> allStudent=studentDao.getAllStudent();
        //2.把学生数据保存到request中
        //去到服务端把数据存放到request,不关心客户端如何处理
        Gson gson=new Gson();
        String students=gson.toJson(allStudent);
        //3.跳转到listStudents.jsp页面
        PrintWriter out=response.getWriter();
        out.write(students);
        //request.getRequestDispatcher("liststudents4.html").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
