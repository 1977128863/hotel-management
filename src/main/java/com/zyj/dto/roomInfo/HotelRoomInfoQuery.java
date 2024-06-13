package com.zyj.dto.roomInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 钟裕京
 * @ClassName: hotelRoomInfo
 * @Description:
 * @date: 2022年11月25日 17:31:10
 */
public class HotelRoomInfoQuery implements Serializable {
    /**
     * 房间id
     */
    private Integer roomId;

    /**
     * 房间编号
     */
    private String roomCode;

    /**
     * 房间大小（平方米）
     */
    private Double roomSize;

    /**
     * 分类id
     */
    private Integer classifyId;

    /**
     * 房间状态
     */
    private Integer roomStatus;

    /**
     * 房间标题
     */
    private String roomTitle;

    /**
     * 房间描述
     */
    private String remark;

    /**
     * 假删除
     */
    private Integer isDelete;

    /**
     * 房间图片1（文件编码）
     */
    private String roomImg1;

    /**
     * room_img2
     */
    private String roomImg2;

    /**
     * room_img3
     */
    private String roomImg3;

    /**
     * room_img4
     */
    private String roomImg4;

    /**
     * room_img5
     */
    private String roomImg5;

    /**
     * 房间积分
     */
    private Integer integral;

    /**
     * create_per
     */
    private String createPer;

    /**
     * create_time
     */
    private Date createTime;

    /**
     * modify_per
     */
    private String modifyPer;

    /**
     * modify_time
     */
    private Date modifyTime;

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

    private static final long serialVersionUID = 1L;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public Double getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(Double roomSize) {
        this.roomSize = roomSize;
    }

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public Integer getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Integer roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getRoomImg1() {
        return roomImg1;
    }

    public void setRoomImg1(String roomImg1) {
        this.roomImg1 = roomImg1;
    }

    public String getRoomImg2() {
        return roomImg2;
    }

    public void setRoomImg2(String roomImg2) {
        this.roomImg2 = roomImg2;
    }

    public String getRoomImg3() {
        return roomImg3;
    }

    public void setRoomImg3(String roomImg3) {
        this.roomImg3 = roomImg3;
    }

    public String getRoomImg4() {
        return roomImg4;
    }

    public void setRoomImg4(String roomImg4) {
        this.roomImg4 = roomImg4;
    }

    public String getRoomImg5() {
        return roomImg5;
    }

    public void setRoomImg5(String roomImg5) {
        this.roomImg5 = roomImg5;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", roomId=").append(roomId);
        sb.append(", roomCode=").append(roomCode);
        sb.append(", roomSize=").append(roomSize);
        sb.append(", classifyId=").append(classifyId);
        sb.append(", roomStatus=").append(roomStatus);
        sb.append(", roomTitle=").append(roomTitle);
        sb.append(", remark=").append(remark);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", roomImg1=").append(roomImg1);
        sb.append(", roomImg2=").append(roomImg2);
        sb.append(", roomImg3=").append(roomImg3);
        sb.append(", roomImg4=").append(roomImg4);
        sb.append(", roomImg5=").append(roomImg5);
        sb.append(", integral=").append(integral);
        sb.append(", createPer=").append(createPer);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyPer=").append(modifyPer);
        sb.append(", modifyTime=").append(modifyTime);
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
        HotelRoomInfoQuery other = (HotelRoomInfoQuery) that;
        return (this.getRoomId() == null ? other.getRoomId() == null : this.getRoomId().equals(other.getRoomId()))
                && (this.getRoomCode() == null ? other.getRoomCode() == null : this.getRoomCode().equals(other.getRoomCode()))
                && (this.getRoomSize() == null ? other.getRoomSize() == null : this.getRoomSize().equals(other.getRoomSize()))
                && (this.getClassifyId() == null ? other.getClassifyId() == null : this.getClassifyId().equals(other.getClassifyId()))
                && (this.getRoomStatus() == null ? other.getRoomStatus() == null : this.getRoomStatus().equals(other.getRoomStatus()))
                && (this.getRoomTitle() == null ? other.getRoomTitle() == null : this.getRoomTitle().equals(other.getRoomTitle()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
                && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
                && (this.getRoomImg1() == null ? other.getRoomImg1() == null : this.getRoomImg1().equals(other.getRoomImg1()))
                && (this.getRoomImg2() == null ? other.getRoomImg2() == null : this.getRoomImg2().equals(other.getRoomImg2()))
                && (this.getRoomImg3() == null ? other.getRoomImg3() == null : this.getRoomImg3().equals(other.getRoomImg3()))
                && (this.getRoomImg4() == null ? other.getRoomImg4() == null : this.getRoomImg4().equals(other.getRoomImg4()))
                && (this.getRoomImg5() == null ? other.getRoomImg5() == null : this.getRoomImg5().equals(other.getRoomImg5()))
                && (this.getIntegral() == null ? other.getIntegral() == null : this.getIntegral().equals(other.getIntegral()))
                && (this.getCreatePer() == null ? other.getCreatePer() == null : this.getCreatePer().equals(other.getCreatePer()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getModifyPer() == null ? other.getModifyPer() == null : this.getModifyPer().equals(other.getModifyPer()))
                && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRoomId() == null) ? 0 : getRoomId().hashCode());
        result = prime * result + ((getRoomCode() == null) ? 0 : getRoomCode().hashCode());
        result = prime * result + ((getRoomSize() == null) ? 0 : getRoomSize().hashCode());
        result = prime * result + ((getClassifyId() == null) ? 0 : getClassifyId().hashCode());
        result = prime * result + ((getRoomStatus() == null) ? 0 : getRoomStatus().hashCode());
        result = prime * result + ((getRoomTitle() == null) ? 0 : getRoomTitle().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getRoomImg1() == null) ? 0 : getRoomImg1().hashCode());
        result = prime * result + ((getRoomImg2() == null) ? 0 : getRoomImg2().hashCode());
        result = prime * result + ((getRoomImg3() == null) ? 0 : getRoomImg3().hashCode());
        result = prime * result + ((getRoomImg4() == null) ? 0 : getRoomImg4().hashCode());
        result = prime * result + ((getRoomImg5() == null) ? 0 : getRoomImg5().hashCode());
        result = prime * result + ((getIntegral() == null) ? 0 : getIntegral().hashCode());
        result = prime * result + ((getCreatePer() == null) ? 0 : getCreatePer().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifyPer() == null) ? 0 : getModifyPer().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        return result;
    }
}