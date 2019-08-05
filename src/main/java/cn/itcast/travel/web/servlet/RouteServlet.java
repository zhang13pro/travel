package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
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
    private FavoriteService favoriteService = new FavoriteServiceImpl();
    //显示所有页
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1接受参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        //2处理参数
        int cid = 0; //类别id
        if (cidStr != null && cidStr.length() > 0) {
            cid = Integer.parseInt(cidStr);
        }
        int currentPage = 0;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        int pageSize = 0; //每页显示条数 默认5
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 5;
        }
        //3 调用service查询pagebean对象
        PageBean<Route> pb = service.pageQuery(cid, currentPage, pageSize);
        //4 将pagebean对象序列化为json返回
        writeValue(pb, response);
    }

    //根据id查询一个旅游线路的详细信息
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1接收客户端rid
        String rid = request.getParameter("rid");
        //2调用service查询route对象
        Route route = service.findOne(rid);
        //3转为json写回客户端
        writeValue(route, response);
    }

    //判断当前用户是否收藏线路
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1获取线路id
        String rid = request.getParameter("rid");
        //2获取当前登陆用户
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user == null){
            uid = 0;
        }else {
            uid = user.getUid();
        }
        //3调用service查询是否收藏
        boolean flag = favoriteService.isFavorite(rid, uid);
        //4写会客户端
        writeValue(flag,response);
    }
}