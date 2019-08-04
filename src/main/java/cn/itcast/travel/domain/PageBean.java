package cn.itcast.travel.domain;

import java.util.List;

/**
 * @Author: bobobo
 * @Date: 2019/8/3 14:55
 * @Version：1.0
 */
public class PageBean<T> {
    private int totalCount;     //总条数
    private int totalPage;      //总页数
    private int currentPage;  //当前页码
    private int pageSize;    //每页显示的条数
    private List<Route> list; //每页显示的数据集合

    public List<Route> getList() {
        return list;
    }

    public void setList(List<Route> list) {
        this.list = list;
    }



    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
