package me.zhengjie.modules.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户角色关联
 * Created by jinjin on 2020-09-25.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(value = { "handler" })
@TableName("sys_roles_menus")
@ApiModel(value="RolesMenus对象", description="角色菜单关联")
public class RolesMenus implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色ID")
    @TableField(value = "role_id")
    private Long roleId;

    @ApiModelProperty(value = "部门ID")
    @TableField(value = "menu_id")
    private Long menuId;
}
