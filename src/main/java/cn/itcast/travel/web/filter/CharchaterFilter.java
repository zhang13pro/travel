package cn.itcast.travel.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: bobobo
 * @Date: 2019/7/30 16:28
 * @Version：1.0
 * 解决全站乱码问题，处理所有的请求
 */

@WebFilter("/*")
public class CharchaterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        //将父接口转为子接口
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //获取请求方法
        String method = request.getMethod();
        //解决post请求中文数据乱码问题
        if(method.equalsIgnoreCase("post")){
            request.setCharacterEncoding("UTF-8");
        }
        //处理响应乱码
        response.setContentType("text/html;charset=UTF-8");
        filterChain.doFilter(request,response);
/*        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        filterChain.doFilter(req,resp);*/
    }


    @Override
    public void destroy() {

    }
}


