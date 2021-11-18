package com.luch.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description：
 */
@Entity
@Table(name = "b_order")
public class BOrder implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 是否被删除
     */
    private Boolean isDel;

    /**
     * 公司ID
     */
    private Integer companyId;

    /**
     * 职位ID
     */
    private Long positionId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 职位发布者id
     */
    private Integer publishUserId;

    /**
     * 简历类型：0 附件 1 在线
     */
    private Integer resumeType;

    /**
     * 投递状态 投递状态 WAIT-待处理 AUTO_FILTER-自动过滤 PREPARE_CONTACT-待沟通 REFUSE-拒绝 ARRANGE_INTERVIEW-通知面试
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 操作时间
     */
    private Date operateTime;

    /**
     * 工作年限
     */
    private String workYear;

    /**
     * 投递简历人名字
     */
    private String name;

    /**
     * 职位名称
     */
    private String positionName;

    /**
     * 投递的简历id（在线和附件都记录，通过resumeType进行区别在线还是附件）
     */
    private Integer resumeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Boolean getDel() {
        return isDel;
    }

    public void setDel(Boolean isDel) {
        this.isDel = isDel;
    }
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getPublishUserId() {
        return publishUserId;
    }

    public void setPublishUserId(Integer publishUserId) {
        this.publishUserId = publishUserId;
    }
    public Integer getResumeType() {
        return resumeType;
    }

    public void setResumeType(Integer resumeType) {
        this.resumeType = resumeType;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    @Override
    public String toString() {
        return "BOrder{" +
            "id=" + id +
            ", isDel=" + isDel +
            ", companyId=" + companyId +
            ", positionId=" + positionId +
            ", userId=" + userId +
            ", publishUserId=" + publishUserId +
            ", resumeType=" + resumeType +
            ", status=" + status +
            ", createTime=" + createTime +
            ", operateTime=" + operateTime +
            ", workYear=" + workYear +
            ", name=" + name +
            ", positionName=" + positionName +
            ", resumeId=" + resumeId +
        "}";
    }
}
