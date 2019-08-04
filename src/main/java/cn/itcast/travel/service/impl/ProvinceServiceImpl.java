package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.ProvinceDao;
import cn.itcast.travel.dao.impl.ProvinceDaoImpl;
import cn.itcast.travel.domain.Province;
import cn.itcast.travel.service.ProvinceService;

import java.util.List;

/**
 * @Author: bobobo
 * @Date: 2019/8/3 11:00
 * @Version：1.0
 */
public class ProvinceServiceImpl implements ProvinceService {
    private ProvinceDao dao = new ProvinceDaoImpl();
    @Override
    public List<Province> findAll() {
        return dao.findAll();
    }
}
