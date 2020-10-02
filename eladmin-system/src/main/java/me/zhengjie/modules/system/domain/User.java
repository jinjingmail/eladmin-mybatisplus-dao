package me.zhengjie.modules.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.EqualsAndHashCode;
import me.zhengjie.base.CommonEntity;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

/**
* @author jinjin
* @date 2020-09-25
*/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class User extends CommonEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "user_id", type= IdType.AUTO)
    @NotNull(groups = Update.class)
    private Long id;

    @ApiModelProperty(value = "部门名称")
    private Long deptId;

    @ApiModelProperty(value = "用户名")
    @NotBlank
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "手机号码")
    @NotBlank
    private String phone;

    @ApiModelProperty(value = "邮箱")
    @NotBlank
    private String email;

    @ApiModelProperty(value = "头像地址")
    private String avatarName;

    @ApiModelProperty(value = "头像真实路径")
    private String avatarPath;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "是否为admin账号")
    private Boolean isAdmin;

    @ApiModelProperty(value = "状态：1启用、0禁用")
    private Boolean enabled;

    @ApiModelProperty(value = "修改密码的时间")
    private Date pwdResetTime;

    public <T> void copyFrom(T source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
