package me.zhengjie.modules.mnt.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

/**
* @author jinjin
* @date 2020-09-27
*/
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("mnt_deploy_history")
public class DeployHistory implements Serializable {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "ID")
    @TableId(value="history_id", type= IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "应用名称")
    @NotBlank
    private String appName;

    @ApiModelProperty(value = "部署日期")
    @NotNull
    private Date deployDate;

    @ApiModelProperty(value = "部署用户")
    @NotBlank
    private String deployUser;

    @ApiModelProperty(value = "服务器IP")
    @NotBlank
    private String ip;

    @ApiModelProperty(value = "部署编号")
    private Long deployId;

    public void copyFrom(DeployHistory source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
