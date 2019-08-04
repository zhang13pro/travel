package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Author: bobobo
 * @Date: 2019/8/1 16:47
 * @Version：1.0
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private final UserService service = new UserServiceImpl();

    /*
     * 用户注册
     * */
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证码
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //验证码只能使用一次
        session.removeAttribute("CHECKCODE_SERVER");
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //将info对象序列化成json
/*            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);*/
            //将json数据写回客户端
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(writeValueAsString(info));
            return;
        }
        //获取数据并封装对象
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用service完成注册
        //UserService service = new UserServiceImpl();
        boolean flag = service.regist(user);
        ResultInfo info = new ResultInfo();
//      if(flag)逻辑 --只有当flag为true时进入循环
        if (flag) {
            info.setFlag(true);
        } else {
            info.setFlag(false);
            info.setErrorMsg("注册失败");
        }
        //将info对象序列化成json以便写会
 /*       ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);*/
        //将json数据写回客户端
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(writeValueAsString(info));
    }

    /*
     * 用户登陆
     * */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1获取用户名密码
        final Map<String, String[]> map = request.getParameterMap();
        //2封装对象
        final User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3调用service查询
        //final UserService service = new UserServiceImpl();
        User u = service.login(user);
        final ResultInfo info = new ResultInfo();
        //4判断是否存在
        if (u == null) {
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        //5判断是否激活
        if (u != null && "N".equals(u.getStatus())) {
            info.setFlag(false);
            info.setErrorMsg("宁尚未激活，请先激活");
        }
        //6登录cg
        if (u != null && "Y".equals(u.getStatus())) {
            info.setFlag(true);
        }
        //7响应数据
        writeValue(info,response);
/*        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), info);*/
    }

    /*
     * 查找用户
     * */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1从session中获取登陆用户
        Object user = request.getSession().getAttribute("user");
        //2写会客户端
        writeValue(user,response);
/*        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), user);*/
    }

    /*
     * 用户退出
     * */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1销毁session
        request.getSession().invalidate();
        //2跳转页面
        response.sendRedirect(request.getContextPath() + "/login.html");
    }

    /*
    * 用户激活
    * */
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取激活码
        String code = request.getParameter("code");
        if (code != null) {
            //调用service完成激活
            //UserService service = new UserServiceImpl();
            boolean flag = service.active(code);
            //判断标记
            String msg = null;
            if (flag) {
                msg = "激活成功，请<a href = 'login.html'>登陆</a>";
            } else {
                msg = "激活失败，请联系管理员";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }
}