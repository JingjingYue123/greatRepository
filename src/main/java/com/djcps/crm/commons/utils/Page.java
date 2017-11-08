package com.djcps.crm.commons.utils;

import java.util.List;

/**
 * 分页
 * Created by truth on 2017-03-11.
 *
 * @param <T> the type parameter
 */
public class Page<T> {
    private int begin; //数据库分页查询时的begin值
    private int currentPage; //当前页
    private int totalSize; //总条数
    private int pageSize; //一个显示的条数
    private int totalPage; //总页数
    private List<T> content;
    private boolean prev;
    private boolean next;

    /**
     * Instantiates a new Page.
     */
    public Page() {
    }

    /**
     * Instantiates a new Page.
     *
     * @param currentPage the current page
     * @param pageSize    the page size
     * @param totalSize   the total size
     */
    public Page(int currentPage, int pageSize, int totalSize) {
        pageSize = pageSize > 0 ? pageSize : 10;
        this.totalSize = totalSize > 0 ? totalSize : 1;
        this.totalPage = totalSize % pageSize > 0 ? totalSize / pageSize + 1 : (totalSize / pageSize > 0 ? totalSize / pageSize: 1);
        this.begin = currentPage > 1 ? (currentPage > totalPage ? totalPage - 1 : currentPage - 1) * pageSize : 0;
        this.currentPage = currentPage > 0 ? (currentPage > totalPage ? totalPage : currentPage) : 1;
        this.pageSize = pageSize;
    }

    /**
     * Instantiates a new Page.
     *
     * @param currentPage 当前页
     * @param pageSize    the page size
     * @param content     the content
     * @param totalSize   the total size
     */
    public Page(int currentPage, int pageSize, List<T> content, int totalSize) {
        pageSize = pageSize > 0 ? pageSize : 10;
        this.totalPage = totalSize % pageSize > 0 ? totalSize / pageSize + 1 : (totalSize / pageSize > 0 ? totalSize / pageSize: 1);
        this.begin = currentPage > 1 ? (currentPage > totalPage ? totalPage - 1 : currentPage - 1) * pageSize : 0;
        this.content = content;
        this.totalSize = totalSize > 0 ? totalSize : 1;
        this.currentPage = currentPage > 0 ? (currentPage > totalPage ? totalPage : currentPage) : 1;
        this.pageSize = pageSize;
    }

    /**
     * Get begin int.
     *
     * @return the int
     */
    public int getBegin() {
        return begin;
    }

    /**
     * Gets current page.
     *
     * @return the current page
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * Sets current page.
     *
     * @param currentPage the currentPage
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage > 0 ? (currentPage > totalPage ? totalPage : currentPage) : 1;
    }

    /**
     * Sets total page.
     *
     * @param totalPage the total page
     */
    public void setTotalPage(final int totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * Gets current page.
     *
     * @param begin the begin
     * @return the current page
     */
    public int getCurrentPage(int begin) {
        return currentPage;
    }

    /**
     * Gets total size.
     *
     * @return the total size
     */
    public int getTotalSize() {
        //select count(*) from f_T where a=x ...
        return totalSize;
    }

    /**
     * Sets total size.
     */
    public void setTotalSize() {
        this.totalSize = totalSize % pageSize > 0 ? totalSize / pageSize + 1 : totalSize / pageSize;
    }

    /**
     * Gets page size.
     *
     * @return the page size
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets page size.
     *
     * @param pageSize the page size
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Gets total page.
     *
     * @return the total page
     */
    public int getTotalPage() {
        return totalSize % pageSize > 0 ? totalSize / pageSize + 1 : (totalSize / pageSize > 0 ? totalSize / pageSize: 1);
    }

    /**
     * Gets content.
     *
     * @return the content
     */
    public List<T> getContent() {
        return content;
    }

    /**
     * Sets content.
     *
     * @param content the content
     */
    public void setContent(List<T> content) {
        this.content = content;
    }

    /**
     * Has prev boolean.
     *
     * @return the boolean
     */
    public boolean hasPrev() {
        return currentPage > 1 && currentPage <= totalPage;
    }

    /**
     * Has next boolean.
     *
     * @return the boolean
     */
    public boolean hasNext() {
        return currentPage > 0 && currentPage < totalPage - 1;
    }

    public boolean isCurrentPageIllegal() {
        return currentPage > 0 && currentPage <= totalPage;
    }
}