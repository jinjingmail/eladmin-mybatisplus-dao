package me.zhengjie.modules.mnt.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import me.zhengjie.base.CommonDto;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

/**
* @author jinjin
* @date 2020-09-27
*/
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DatabaseDto extends CommonDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String jdbcUrl;

    private String userName;

    private String pwd;
}
