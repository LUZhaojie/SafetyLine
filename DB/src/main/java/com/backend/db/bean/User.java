package com.backend.db.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("user")
public class User {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String username;

    @TableField(value="password",fill = FieldFill.DEFAULT)
    private String password;

    private String email;

    @TableField(value="role",fill = FieldFill.DEFAULT)
    private Integer role;
}
