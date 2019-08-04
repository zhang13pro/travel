package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author bobobo
 * @version 1.0
 * @date 2019/7/31 23:32
 * 登录时查询是否注册
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1获取用户名密码
        final Map<String, String[]> map = request.getParameterMap();
        //2封装对象
        final User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3调用service查询
        final UserService service = new UserServiceImpl();
        User u = service.login(user);
        final ResultInfo info = new ResultInfo();
        //4判断是否存在
        if (u == null){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        //5判断是否激活
        if (u != null && "N".equals(u.getStatus())){
            info.setFlag(false);
            info.setErrorMsg("宁尚未激活，请先激活");
        }
        //6登录cg
        if (u != null && "Y".equals(u.getStatus())){
            info.setFlag(true);
        }
        //7响应数据
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
