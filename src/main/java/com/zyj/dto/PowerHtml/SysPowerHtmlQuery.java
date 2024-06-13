package com.zyj.dto.PowerHtml;

import java.io.Serializable;

/**
 * @author 钟裕京
 * @ClassName: SysPowerHtml
 * @Description:
 * @date: 2022年11月17日 17:24:05
 */
public class SysPowerHtmlQuery implements Serializable {
    /**
     * 页面id
     */
    private Integer htmlId;

    /**
     * 页面路径
     */
    private String htmlHref;

    /**
     * menu_id
     */
    private Integer menuId;

    private Integer limit;

    private Integer start;

    private Integer page;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;

    public Integer getHtmlId() {
        return htmlId;
    }

    public void setHtmlId(Integer htmlId) {
        this.htmlId = htmlId;
    }

    public String getHtmlHref() {
        return htmlHref;
    }

    public void setHtmlHref(String htmlHref) {
        this.htmlHref = htmlHref;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", htmlId=").append(htmlId);
        sb.append(", htmlHref=").append(htmlHref);
        sb.append(", menuId=").append(menuId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysPowerHtmlQuery other = (SysPowerHtmlQuery) that;
        return (this.getHtmlId() == null ? other.getHtmlId() == null : this.getHtmlId().equals(other.getHtmlId()))
                && (this.getHtmlHref() == null ? other.getHtmlHref() == null : this.getHtmlHref().equals(other.getHtmlHref()))
                && (this.getMenuId() == null ? other.getMenuId() == null : this.getMenuId().equals(other.getMenuId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getHtmlId() == null) ? 0 : getHtmlId().hashCode());
        result = prime * result + ((getHtmlHref() == null) ? 0 : getHtmlHref().hashCode());
        result = prime * result + ((getMenuId() == null) ? 0 : getMenuId().hashCode());
        return result;
    }
}