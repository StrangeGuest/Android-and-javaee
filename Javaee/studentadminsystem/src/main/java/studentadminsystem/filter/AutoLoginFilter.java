package studentadminsystem.filter;

import studentadminsystem.Dao.UserDao;
import studentadminsystem.Dao.impl.UserDaoImpl;
import studentadminsystem.model.User;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AutoLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.把ServletRequest转化为HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //2.获取cookie,是保存在client端的key--value键值对,多个键值对组成数组
        Cookie[] cookies=request.getCookies();
        //3.在cookie中查找用户名key:autologin,把值保存到autologin中
        String autologin=null;
        for(Cookie c:cookies){
            if(c.getName().equalsIgnoreCase("autologin")){
                autologin=c.getValue();
            }
        }
        //4.判断autologin是否为null,null表示子cookie中没有找到,not null 找到
        if(autologin!=null){
            //自动登录
            //把真正的用户名与密码表示为:"zhangsan-123456"
            String[] users = autologin.split("-");
            String username=users[0];
            String password=users[1];
            User u=new User(username,password);
            boolean result = new UserDaoImpl().checkLogin(u);
            if(result){
                //登录成功,把用户信息放入session中
                request.getSession().setAttribute("user",u);
            }
        }
        //放行
        filterChain.doFilter(request,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
