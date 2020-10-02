package ${package}.service;

import me.zhengjie.base.PageInfo;
import ${package}.domain.${className};
import ${package}.service.dto.${className}Dto;
import ${package}.service.dto.${className}QueryParam;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Set;

/**
* @author ${author}
* @date ${date}
*/
public interface ${className}Service {

    static final String CACHE_KEY = "${changeClassName}";

    /**
    * 查询数据分页
    * @param query 条件
    * @param pageable 分页参数
    * @return PageInfo<${className}Dto>
    */
    PageInfo<${className}Dto> queryAll(${className}QueryParam query, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param query 条件参数
    * @return List<${className}Dto>
    */
    List<${className}Dto> queryAll(${className}QueryParam query);

    ${className} getById(${pkColumnType} id);
    ${className}Dto findById(${pkColumnType} id);

    /**
     * 插入一条新数据。
     */
    int insert(${className}Dto resources);
    int updateById(${className}Dto resources);
    int removeById(${pkColumnType} id);
    int removeByIds(Set<${pkColumnType}> ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    // void download(List<${className}Dto> all, HttpServletResponse response) throws IOException;
}
