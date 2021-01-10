package studentadminsystem.servlet;

import studentadminsystem.Dao.impl.UserDaoImpl;
import studentadminsystem.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        //1.获取用户提交的数据
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        String userName=request.getParameter("username");
        String password=request.getParameter("password");
        System.out.println(userName + ',' + password);
        User user=new User(userName,password);
        UserDaoImpl userDao=new UserDaoImpl();
        boolean result=userDao.checkLogin(user);
        System.out.println(result);
        if(result){
            request.getRequestDispatcher("liststudents.jsp").forward(request,response);
            //response.sendRedirect("liststudents.jsp");
        }else{
            PrintWriter out=response.getWriter();
            out.write("<script>alert('用户名或密码错误');location.href=('login.html');</script>");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

