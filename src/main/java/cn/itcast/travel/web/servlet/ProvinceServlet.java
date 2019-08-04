package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Province;
import cn.itcast.travel.service.ProvinceService;
import cn.itcast.travel.service.impl.ProvinceServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: bobobo
 * @Date: 2019/8/3 11:24
 * @Version：1.0
 */
@WebServlet("/provinceServlet")
public class ProvinceServlet extends BaseServlet {
    private ProvinceService service = new ProvinceServiceImpl();
    public void province(){
        //1调用service
        List<Province> list = service.findAll();
        //2序列化json
        try {
            writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //3响应结果

    }
}
