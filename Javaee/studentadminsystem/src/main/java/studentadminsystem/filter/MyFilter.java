package studentadminsystem.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/*public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter.init()方法被调用");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //System.out.println("MyFilter.doFilter()方法开始工作");
        //检查用户是否登录，如果登录，跳转到登陆界面
        //1.如何判断用户是否已经登录？session.getAttribute("user"),是否存在,
        //如果存在则认为用户已经登陆，不存在用户没有登录
        //用户已经登陆，不做任何处理，
        //用户没有登陆，跳转到登录页面

        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        Object user=session.getAttribute("user");
        if(user==null){
            PrintWriter writer=((HttpServletResponse)servletResponse).getWriter();
            writer.write("<script>alert('用户未登录,请登录后再访问！');location.href='index.html'</script>");
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }




    }

    @Override
    public void destroy() {
        System.out.println("MyFilter.destory()方法被调用,MyFilter被销毁了！");
    }
}*/
