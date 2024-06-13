package com.zyj.dto.Order;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 钟裕京
 * @ClassName: hotelOrder
 * @Description:
 * @date: 2022年11月26日 22:23:50
 */
public class HotelOrder implements Serializable {
    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 房间编号
     */
    private String roomCode;

    /**
     * 房间价格
     */
    private Long roomPrice;

    /**
     * 房间大小
     */
    private Integer roomSize;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:ss:mm", locale = "zh", timezone = "GMT+8")
    private Date createTime;

    /**
     * 订单状态（取数字字典）
     */
    private Integer orderStatus;

    /**
     * 联系人手机号
     */
    private String userPhone;

    /**
     * 订单联系人
     */
    private String contacts;

    /**
     * 身份证号
     */
    private String idCard;

    private static final long serialVersionUID = 1L;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public Long getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Long roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Integer getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(Integer roomSize) {
        this.roomSize = roomSize;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", userId=").append(userId);
        sb.append(", roomCode=").append(roomCode);
        sb.append(", roomPrice=").append(roomPrice);
        sb.append(", roomSize=").append(roomSize);
        sb.append(", createTime=").append(createTime);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", userPhone=").append(userPhone);
        sb.append(", contacts=").append(contacts);
        sb.append(", idCard=").append(idCard);
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
        HotelOrder other = (HotelOrder) that;
        return (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getRoomCode() == null ? other.getRoomCode() == null : this.getRoomCode().equals(other.getRoomCode()))
                && (this.getRoomPrice() == null ? other.getRoomPrice() == null : this.getRoomPrice().equals(other.getRoomPrice()))
                && (this.getRoomSize() == null ? other.getRoomSize() == null : this.getRoomSize().equals(other.getRoomSize()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getOrderStatus() == null ? other.getOrderStatus() == null : this.getOrderStatus().equals(other.getOrderStatus()))
                && (this.getUserPhone() == null ? other.getUserPhone() == null : this.getUserPhone().equals(other.getUserPhone()))
                && (this.getContacts() == null ? other.getContacts() == null : this.getContacts().equals(other.getContacts()))
                && (this.getIdCard() == null ? other.getIdCard() == null : this.getIdCard().equals(other.getIdCard()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getRoomCode() == null) ? 0 : getRoomCode().hashCode());
        result = prime * result + ((getRoomPrice() == null) ? 0 : getRoomPrice().hashCode());
        result = prime * result + ((getRoomSize() == null) ? 0 : getRoomSize().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getOrderStatus() == null) ? 0 : getOrderStatus().hashCode());
        result = prime * result + ((getUserPhone() == null) ? 0 : getUserPhone().hashCode());
        result = prime * result + ((getContacts() == null) ? 0 : getContacts().hashCode());
        result = prime * result + ((getIdCard() == null) ? 0 : getIdCard().hashCode());
        return result;
    }
}