package me.zhengjie.modules.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import me.zhengjie.base.CommonEntity;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
* @author jinjin
* @date 2020-09-25
*/
@Data
@TableName("sys_dept")
public class Dept extends CommonEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value="dept_id", type= IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "上级部门")
    @NotNull
    private Long pid;

    @ApiModelProperty(value = "子部门数目")
    private Integer subCount;

    @ApiModelProperty(value = "名称")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "排序")
    private Integer deptSort;

    @ApiModelProperty(value = "状态")
    @NotNull
    private Boolean enabled;

    public void copyFrom(Dept source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
