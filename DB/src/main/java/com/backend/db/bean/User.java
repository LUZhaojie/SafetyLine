package com.backend.db.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
@TableName("user")
public class User {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String username;

    @TableField(value="password",fill = FieldFill.DEFAULT)
    private String password;

    @Email(message = "Error format Email")
    private String email;

    @TableField(value="role",fill = FieldFill.DEFAULT)
    private Integer role;
}
