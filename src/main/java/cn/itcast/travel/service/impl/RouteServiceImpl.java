package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

/**
 * @Author: bobobo
 * @Date: 2019/8/3 15:42
 * @Version：1.0
 */
public class RouteServiceImpl implements RouteService {
    private RouteDao dao = new RouteDaoImpl();
    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize) {
        //封装pageBean
        PageBean<Route> pb = new PageBean<>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //每页显示条数
        pb.setPageSize(pageSize);
       //总记录数
        int totalCount = dao.findTotalCount(cid);
        pb.setTotalCount(totalCount);
        //当前页显示的数据集合
        int start = (currentPage - 1)*pageSize;
        List<Route> list = dao.findByPage(cid, start, pageSize);
        pb.setList(list);
        //总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount/pageSize : totalCount/pageSize +1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public Route findOne(String rid) {
        //1根据id从route表查询route对象
        Route route = dao.findOne(Integer.parseInt(rid));
        //2根据rid查询图片集合信息
        List<RouteImg> list = routeImgDao.findByRid(route.getRid());
        route.setRouteImgList(list);
        //3根据route的sid 查询商家对象
        Seller seller = sellerDao.findBySid(route.getSid()) ;
        route.setSeller(seller);
        return route;
    }
}
