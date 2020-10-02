package ${package}.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
<#if columns??>
    <#list columns as column>
        <#if column.columnKey = 'UNI'>
            <#if column_index = 1>
import me.zhengjie.exception.EntityExistException;
            </#if>
        </#if>
    </#list>
</#if>
import lombok.AllArgsConstructor;
import me.zhengjie.base.PageInfo;
import me.zhengjie.base.QueryHelpMybatisPlus;
import me.zhengjie.utils.ConvertUtil;
import me.zhengjie.utils.PageUtil;
import ${package}.domain.${className};
import ${package}.service.${className}Service;
import ${package}.service.dto.${className}Dto;
import ${package}.service.dto.${className}QueryParam;
import ${package}.service.mapper.${className}Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import java.util.*;

/**
* @author ${author}
* @date ${date}
*/
@Service
@AllArgsConstructor
// @CacheConfig(cacheNames = ${className}Service.CACHE_KEY)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ${className}ServiceImpl implements ${className}Service {

    // private final RedisUtils redisUtils;
    private final ${className}Mapper ${changeClassName}Mapper;

    @Override
    public PageInfo<${className}Dto> queryAll(${className}QueryParam query, Pageable pageable) {
        IPage<${className}> queryPage = PageUtil.toMybatisPage(pageable);
        IPage<${className}> page = ${changeClassName}Mapper.selectPage(queryPage, QueryHelpMybatisPlus.getPredicate(query));
        return ConvertUtil.convertPage(page, ${className}Dto.class);
    }

    @Override
    public List<${className}Dto> queryAll(${className}QueryParam query){
        return ConvertUtil.convertList(${changeClassName}Mapper.selectList(QueryHelpMybatisPlus.getPredicate(query)), ${className}Dto.class);
    }

    @Override
    public ${className} getById(${pkColumnType} id) {
        return ${changeClassName}Mapper.selectById(id);
    }

    @Override
    // @Cacheable(key = "'id:' + #p0")
    public ${className}Dto findById(${pkColumnType} id) {
        return ConvertUtil.convert(getById(id), ${className}Dto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(${className}Dto resources) {
        ${className} entity = ConvertUtil.convert(resources, ${className}.class);
        return ${changeClassName}Mapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(${className}Dto resources){
        ${className} entity = ConvertUtil.convert(resources, ${className}.class);
        int ret = ${changeClassName}Mapper.updateById(entity);
        // delCaches(resources.id);
        return ret;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeByIds(Set<${pkColumnType}> ids){
        // delCaches(ids);
        return ${changeClassName}Mapper.deleteBatchIds(ids);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeById(${pkColumnType} id){
        Set<${pkColumnType}> set = new HashSet<>(1);
        set.add(id);
        return this.removeByIds(set);
    }

    /*
    private void delCaches(${pkColumnType} id) {
        redisUtils.delByKey(CACHE_KEY + "::id:", id);
    }

    private void delCaches(Set<${pkColumnType}> ids) {
        for (${pkColumnType} id: ids) {
            delCaches(id);
        }
    }*/

    /*
    @Override
    public void download(List<${className}Dto> all, HttpServletResponse response) throws IOException {
      List<Map<String, Object>> list = new ArrayList<>();
      for (${className}Dto ${changeClassName} : all) {
        Map<String,Object> map = new LinkedHashMap<>();
        <#list columns as column>
          <#if column.columnKey != 'PRI'>
            <#if column.remark != ''>
              map.put("${column.remark}", ${changeClassName}.get${column.capitalColumnName}());
              <#else>
                map.put(" ${column.changeColumnName}",  ${changeClassName}.get${column.capitalColumnName}());
            </#if>
          </#if>
        </#list>
        list.add(map);
      }
      FileUtil.downloadExcel(list, response);
    }*/
}
