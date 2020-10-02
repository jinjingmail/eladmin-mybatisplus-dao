package me.zhengjie.domain;

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

/**
* @author jinjin
* @date 2020-09-27
*/
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("tool_alipay_config")
public class AlipayConfig implements Serializable {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "ID")
    @TableId(value = "config_id", type= IdType.ASSIGN_ID)
    private Long id;

    @NotBlank
    @ApiModelProperty(value = "应用ID")
    private String appId;

    @ApiModelProperty(value = "编码", hidden = true)
    private String charset= "utf-8";

    @ApiModelProperty(value = "类型 固定格式json")
    private String format = "JSON";;

    @ApiModelProperty(value = "支付宝开放安全地址", hidden = true)
    private String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    @ApiModelProperty(value = "异步通知地址")
    private String notifyUrl;

    @NotBlank
    @ApiModelProperty(value = "商户私钥")
    private String privateKey;

    @NotBlank
    @ApiModelProperty(value = "支付宝公钥")
    private String publicKey;

    @NotBlank
    @ApiModelProperty(value = "订单完成后返回的页面")
    private String returnUrl;

    @ApiModelProperty(value = "签名方式")
    private String signType = "RSA2";

    @NotBlank
    @ApiModelProperty(value = "商户号")
    private String sysServiceProviderId;

    public void copyFrom(AlipayConfig source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
