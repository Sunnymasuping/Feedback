package com.back.bean;

import java.io.Serializable;

/**
 * 评教指标表
 * @TableName t_index
 */
public class Index implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 指标内容
     */
    private String content;

    /**
     * 创建时间
     */
    private String createtime;

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 指标内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 指标内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 创建时间
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * 创建时间
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", content=").append(content);
        sb.append(", createtime=").append(createtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}