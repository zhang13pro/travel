package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: bobobo
 * @Date: 2019/8/3 15:00
 * @Version：1.0
 */
@WebServlet("/route/*")
/*
* 分页查询
* */
public class RouteServlet extends BaseServlet {
    private RouteService service = new RouteServiceImpl();

    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1接受参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        //2处理参数
        int cid = 0; //类别id
        if (cidStr != null && cidStr.length() > 0){
            cid = Integer.parseInt(cidStr);
        }
        int currentPage = 0;
        if (currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else {
            currentPage = 1;
        }
        int pageSize = 0; //每页显示条数 默认5
        if (pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else {
            pageSize = 5;
        }
        //3 调用service查询pagebean对象
        PageBean<Route> pb = service.pageQuery(cid, currentPage, pageSize);
        //4 将pagebean对象序列化为json返回
        writeValue(pb,response);
    }
}
