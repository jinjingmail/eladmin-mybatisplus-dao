package me.zhengjie.modules.system.service.dto;

import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.zhengjie.annotation.DataPermission;
import me.zhengjie.annotation.Query;
import org.springframework.format.annotation.DateTimeFormat;

/**
* @author jinjin
* @date 2020-09-25
*/
@Data
// @DataPermission(fieldName = "dept_id")
public class UserQueryParam{

    /** 精确 */
    @Query
    private Long userId;

    private Long deptId;

    @Query(propName = "dept_id", type = Query.Type.IN)
    private Set<Long> deptIds = new HashSet<>();

    @Query(blurry = "email,username,nickName")
    private String blurry;

    /** 精确 */
    @Query
    private Long enabled;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Query(type = Query.Type.BETWEEN)
    private List<Date> createTime;
}
