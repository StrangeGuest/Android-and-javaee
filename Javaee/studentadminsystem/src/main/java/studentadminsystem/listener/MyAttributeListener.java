package studentadminsystem.listener;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionListener;
@WebListener
public class MyAttributeListener implements ServletContextAttributeListener, HttpSessionAttributeListener, ServletRequestAttributeListener {
    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        String name=servletContextAttributeEvent.getName();
        System.out.println("ServletContext添加属性:" + name + "=" +
                servletContextAttributeEvent.
                        getServletContext().
                        getAttribute(name));
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
        String name=servletContextAttributeEvent.getName();
        System.out.println("ServletContext移除属性:" + name);

    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        String name=servletContextAttributeEvent.getName();
        System.out.println("ServletContext替换属性:" + name + "=" +
                servletContextAttributeEvent.
                        getServletContext().
                        getAttribute(name));
    }

    @Override
    public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent) {

    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent) {

    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent) {

    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        String name = httpSessionBindingEvent.getName();
        System.out.println("Session添加属性:" + name + "=" + httpSessionBindingEvent.getSession().getAttribute(name));
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        String name = httpSessionBindingEvent.getName();
        System.out.println("Session移除属性:" + name);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        String name = httpSessionBindingEvent.getName();
        System.out.println("Session改变属性:" + name + "=" + httpSessionBindingEvent.getSession().getAttribute(name));
    }
}
