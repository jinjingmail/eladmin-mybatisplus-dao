package me.zhengjie.modules.quartz.service.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.Date;
import me.zhengjie.annotation.Query;
import org.springframework.format.annotation.DateTimeFormat;

/**
* @author jinjin
* @date 2020-09-27
*/
@Getter
@Setter
public class QuartzJobQueryParam{

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String jobName;

    /** BETWEEN */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Query(type = Query.Type.BETWEEN)
    private List<Date> createTime;
}
