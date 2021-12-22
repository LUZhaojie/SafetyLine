package com.backend.db.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
@TableName("gitlab")
public class Tache {


    @TableId(value = "id")
    private Integer id;

    private String title;

    private String humantimeestimate;

    private String description;

    private String assignee;

    @TableField(value="updated",fill = FieldFill.DEFAULT)
    private Integer updated;

    private Long time;

    private String editor;


    public Tache(Integer id, String title) {
        this.id = id;
        this.title = title;
        this.humantimeestimate = null;
        this.description = null;
        this.assignee = null;
        this.editor = null;
        this.time = null;
    }


    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHumanTimeEstimate() {
        return humantimeestimate;
    }

    public void setHumanTimeEstimate(String humanTimeEstimate) {
        this.humantimeestimate = humanTimeEstimate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUpdated() {
        return updated;
    }

    public void setUpdated(Integer updated) {
        this.updated = updated;
    }
}
