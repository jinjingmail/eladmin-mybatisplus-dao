package me.zhengjie.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
* @author jinjin
* @date 2020-09-27
*/
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmailConfigDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 防止精度丢失 */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    private String fromUser;

    private String host;

    private String pass;

    private String port;

    private String user;
}
