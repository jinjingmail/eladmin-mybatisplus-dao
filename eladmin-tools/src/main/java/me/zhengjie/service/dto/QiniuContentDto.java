package me.zhengjie.service.dto;

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
public class QiniuContentDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String bucket;

    private String name;

    private String size;

    private String type;

    private String url;

    private String suffix;

    private Date updateTime;
}
