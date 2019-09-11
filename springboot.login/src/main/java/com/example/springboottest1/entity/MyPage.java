package com.example.springboottest1.entity;

/**
 * 类MyPage的实现描述：TODO 类实现描述 
 * @author DELL 2019/9/6 14:58
 */
public class MyPage {

    private int currentPage;
    private int pageSize;

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
