package me.zhengjie.modules.system.service.dto;

import lombok.Data;
import java.util.List;
import me.zhengjie.annotation.Query;

/**
* @author jinjin
* @date 2020-09-24
*/
@Data
public class DictQueryParam{

    @Query(blurry = "name,description")
    private String blurry;

    /** 精确 */
    @Query
    private Long dictId;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String name;
}
