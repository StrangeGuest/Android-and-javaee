package studentadminsystem.servlet;

import studentadminsystem.Dao.UserDao;
import studentadminsystem.Dao.impl.UserDaoImpl;
import studentadminsystem.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username.equals("")){
            response.getWriter().write("<script>alert('用户名不能为空');</script>");
        }else if(password.equals("")){
            response.getWriter().write("<script>alert('密码不能为空');</script>");
        }
        else {
            User u = new User(username, password);
            UserDaoImpl userDao = new UserDaoImpl();
            boolean result = userDao.save(u);
            if (result) {
                request.getRequestDispatcher("login.html").forward(request, response);
            } else {
                response.getWriter().write("<script>alert('注册失败');</script>");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
