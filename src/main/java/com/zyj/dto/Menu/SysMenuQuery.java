package com.zyj.dto.Menu;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 钟裕京
 * @ClassName: SysMenu
 * @Description:
 * @date: 2022年11月21日 16:20:56
 */
public class SysMenuQuery implements Serializable {
    /**
     * 菜单id
     */
    private Integer menuId;

    /**
     * 菜单名称
     */
    private String title;

    /**
     * 菜单顺序号
     */
    private Integer orders;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 假删除
     */
    private Integer isDelete;

    /**
     * 是否展开
     */
    private String spread;

    /**
     * 路由
     */
    private String href;

    /**
     * 创建人
     */
    private String createPer;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String modifyPer;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 上级id
     */
    private Integer topMenuId;

    /**
     * 菜单编号
     */
    private String menuCode;

    private Integer start;

    private Integer page;

    private Integer limit;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getSpread() {
        return spread;
    }

    public void setSpread(String spread) {
        this.spread = spread;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getCreatePer() {
        return createPer;
    }

    public void setCreatePer(String createPer) {
        this.createPer = createPer;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyPer() {
        return modifyPer;
    }

    public void setModifyPer(String modifyPer) {
        this.modifyPer = modifyPer;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getTopMenuId() {
        return topMenuId;
    }

    public void setTopMenuId(Integer topMenuId) {
        this.topMenuId = topMenuId;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", menuId=").append(menuId);
        sb.append(", title=").append(title);
        sb.append(", orders=").append(orders);
        sb.append(", icon=").append(icon);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", spread=").append(spread);
        sb.append(", href=").append(href);
        sb.append(", createPer=").append(createPer);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyPer=").append(modifyPer);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", topMenuId=").append(topMenuId);
        sb.append(", menuCode=").append(menuCode);
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
        SysMenuQuery other = (SysMenuQuery) that;
        return (this.getMenuId() == null ? other.getMenuId() == null : this.getMenuId().equals(other.getMenuId()))
                && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
                && (this.getOrders() == null ? other.getOrders() == null : this.getOrders().equals(other.getOrders()))
                && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
                && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
                && (this.getSpread() == null ? other.getSpread() == null : this.getSpread().equals(other.getSpread()))
                && (this.getHref() == null ? other.getHref() == null : this.getHref().equals(other.getHref()))
                && (this.getCreatePer() == null ? other.getCreatePer() == null : this.getCreatePer().equals(other.getCreatePer()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getModifyPer() == null ? other.getModifyPer() == null : this.getModifyPer().equals(other.getModifyPer()))
                && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
                && (this.getTopMenuId() == null ? other.getTopMenuId() == null : this.getTopMenuId().equals(other.getTopMenuId()))
                && (this.getMenuCode() == null ? other.getMenuCode() == null : this.getMenuCode().equals(other.getMenuCode()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMenuId() == null) ? 0 : getMenuId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getOrders() == null) ? 0 : getOrders().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getSpread() == null) ? 0 : getSpread().hashCode());
        result = prime * result + ((getHref() == null) ? 0 : getHref().hashCode());
        result = prime * result + ((getCreatePer() == null) ? 0 : getCreatePer().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifyPer() == null) ? 0 : getModifyPer().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getTopMenuId() == null) ? 0 : getTopMenuId().hashCode());
        result = prime * result + ((getMenuCode() == null) ? 0 : getMenuCode().hashCode());
        return result;
    }
}