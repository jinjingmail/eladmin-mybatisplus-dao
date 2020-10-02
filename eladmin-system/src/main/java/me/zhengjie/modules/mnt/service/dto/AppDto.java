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
public class AppDto extends CommonDto implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long id;

    private String name;

    private String uploadPath;

    private String deployPath;

    private String backupPath;

    private Integer port;

    private String startScript;

    private String deployScript;

}
