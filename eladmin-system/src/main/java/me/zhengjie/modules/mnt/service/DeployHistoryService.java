package me.zhengjie.modules.mnt.service;

import me.zhengjie.base.CommonService;
import me.zhengjie.base.PageInfo;
import me.zhengjie.modules.mnt.domain.DeployHistory;
import me.zhengjie.modules.mnt.service.dto.DeployHistoryDto;
import me.zhengjie.modules.mnt.service.dto.DeployHistoryQueryParam;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.io.IOException;
import java.util.Set;

/**
* @author jinjin
* @date 2020-09-27
*/
public interface DeployHistoryService  extends CommonService<DeployHistory>{

    static final String CACHE_KEY = "deployHistory";

    /**
    * 查询数据分页
    * @param query 条件
    * @param pageable 分页参数
    * @return PageInfo<DeployHistoryDto>
    */
    PageInfo<DeployHistoryDto> queryAll(DeployHistoryQueryParam query, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param query 条件参数
    * @return List<DeployHistoryDto>
    */
    List<DeployHistoryDto> queryAll(DeployHistoryQueryParam query);

    DeployHistory getById(Long id);
    DeployHistoryDto findById(Long id);

    /**
     * 插入一条新数据。
     */
    boolean save(DeployHistory resources);
    boolean updateById(DeployHistory resources);
    boolean removeById(String id);
    boolean removeByIds(Set<String> ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<DeployHistoryDto> all, HttpServletResponse response) throws IOException;
}
