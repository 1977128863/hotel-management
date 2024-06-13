package com.zyj.dto.roomClassify;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 钟裕京
 * @ClassName: hotelRoomClassify
 * @Description:
 * @date: 2022年11月25日 17:30:01
 */
public class HotelRoomClassifyQuery implements Serializable {
    /**
     * 分类id
     */
    private Integer classifyId;

    /**
     * 分类名称
     */
    private String classifyName;

    /**
     * 上级id
     */
    private Integer topClassifyId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createPer;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 修改人
     */
    private String modifyPer;

    /**
     * price
     */
    private BigDecimal price;

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

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public Integer getTopClassifyId() {
        return topClassifyId;
    }

    public void setTopClassifyId(Integer topClassifyId) {
        this.topClassifyId = topClassifyId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatePer() {
        return createPer;
    }

    public void setCreatePer(String createPer) {
        this.createPer = createPer;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyPer() {
        return modifyPer;
    }

    public void setModifyPer(String modifyPer) {
        this.modifyPer = modifyPer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", classifyId=").append(classifyId);
        sb.append(", classifyName=").append(classifyName);
        sb.append(", topClassifyId=").append(topClassifyId);
        sb.append(", createTime=").append(createTime);
        sb.append(", createPer=").append(createPer);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", modifyPer=").append(modifyPer);
        sb.append(", price=").append(price);
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
        HotelRoomClassifyQuery other = (HotelRoomClassifyQuery) that;
        return (this.getClassifyId() == null ? other.getClassifyId() == null : this.getClassifyId().equals(other.getClassifyId()))
                && (this.getClassifyName() == null ? other.getClassifyName() == null : this.getClassifyName().equals(other.getClassifyName()))
                && (this.getTopClassifyId() == null ? other.getTopClassifyId() == null : this.getTopClassifyId().equals(other.getTopClassifyId()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getCreatePer() == null ? other.getCreatePer() == null : this.getCreatePer().equals(other.getCreatePer()))
                && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
                && (this.getModifyPer() == null ? other.getModifyPer() == null : this.getModifyPer().equals(other.getModifyPer()))
                && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getClassifyId() == null) ? 0 : getClassifyId().hashCode());
        result = prime * result + ((getClassifyName() == null) ? 0 : getClassifyName().hashCode());
        result = prime * result + ((getTopClassifyId() == null) ? 0 : getTopClassifyId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreatePer() == null) ? 0 : getCreatePer().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getModifyPer() == null) ? 0 : getModifyPer().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        return result;
    }
}