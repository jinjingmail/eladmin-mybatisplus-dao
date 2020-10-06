package me.zhengjie.modules.system.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.zhengjie.base.DataDto;

import java.io.Serializable;

/**
* @author jinjin
* @date 2020-09-24
*/
@Getter
@Setter
@NoArgsConstructor
public class DictDto extends DataDto implements Serializable {

    private Long id;

    //     private List<DictDetailDto> dictDetails;

    private String name;

    private String description;
}
