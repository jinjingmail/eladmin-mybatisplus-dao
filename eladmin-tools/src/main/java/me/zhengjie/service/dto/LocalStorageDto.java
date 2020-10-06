package me.zhengjie.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import me.zhengjie.base.DataDto;

import java.io.Serializable;

/**
* @author jinjin
* @date 2020-09-27
*/
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LocalStorageDto extends DataDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String realName;

    private String name;

    private String suffix;

    private String path;

    private String type;

    private String size;
}
