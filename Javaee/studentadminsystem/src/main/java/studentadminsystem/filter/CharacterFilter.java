package studentadminsystem.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CharacterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.获取request与response
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //2.拦截所有request与response请求
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //3.包装request对象，增强getParameter()功能，中文不乱码
        CharacterRequest characterRequest = new CharacterRequest(request);
        //4.放行
        filterChain.doFilter(characterRequest,response);

    }

    @Override
    public void destroy() {

    }
    class CharacterRequest extends HttpServletRequestWrapper{
        //1.包装对象
        private HttpServletRequest request;
        public CharacterRequest(HttpServletRequest request) {
            super(request);
            this.request=request;
        }
        //重写getParameter方法
        @Override
        public String getParameter(String name) {
            String value=super.getParameter(name);
            if(value==null){
                return null;
            }
            String method = super.getMethod();
            //如果是get方式请求，需要进行编码转换
            if(method.equalsIgnoreCase("get")){
                //把西文转化为中文编码
                try {
                    value=new String(value.getBytes("iso-8859-1"),"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }

            return value;
        }
    }
/**
 *1.在一个java类中可以定义多个类,但是只能有一个public类
 *2. public类名要与文件名-致
 *3.在java中有很多诸如xxxWrapper的类,这种类是包装类,遵循装饰者模式,需要一个被包装或者装饰对象
 *4.可以增强部分的方法, 比如I0流
 *BufferedReader(InputStreamReader(inputStream))
  本例中CharacterRequest装饰request ,增强getParameter
  方法
*/

}
