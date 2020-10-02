package me.zhengjie.modules.system.service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.zhengjie.base.CommonDto;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

/**
* @author jinjin
* @date 2020-09-24
*/
@Getter
@Setter
@NoArgsConstructor
public class DictDto extends CommonDto implements Serializable {

    private Long id;

    //     private List<DictDetailDto> dictDetails;

    private String name;

    private String description;
}
