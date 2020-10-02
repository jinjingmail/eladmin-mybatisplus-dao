package me.zhengjie.service.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import me.zhengjie.base.CommonMapper;
import me.zhengjie.domain.ColumnInfo;
import me.zhengjie.domain.vo.TableInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author jinjin
* @date 2020-09-25
*/
@Repository
public interface ColumnInfoMapper extends CommonMapper<ColumnInfo> {

    List<TableInfo> getTables();

    int getTablesTotal();

    IPage<TableInfo> selectPageOfTables(IPage<?> page, @Param("name") String tableName);

    List<ColumnInfo> queryColumnInfo(String tableName);
}
