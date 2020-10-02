package me.zhengjie.modules.mnt.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;
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
public class DeployHistoryDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String appName;

    private Date deployDate;

    private String deployUser;

    private String ip;

    private Long deployId;
}
