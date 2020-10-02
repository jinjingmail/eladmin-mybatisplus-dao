package me.zhengjie.modules.mnt.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import me.zhengjie.base.PageInfo;
import me.zhengjie.base.QueryHelpMybatisPlus;
import me.zhengjie.base.impl.CommonServiceImpl;
import me.zhengjie.utils.ConvertUtil;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.modules.mnt.domain.DeployHistory;
import me.zhengjie.modules.mnt.service.DeployHistoryService;
import me.zhengjie.modules.mnt.service.dto.DeployHistoryDto;
import me.zhengjie.modules.mnt.service.dto.DeployHistoryQueryParam;
import me.zhengjie.modules.mnt.mapper.DeployHistoryMapper;
import me.zhengjie.utils.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
* @author jinjin
* @date 2020-09-27
*/
@Service
@AllArgsConstructor
// @CacheConfig(cacheNames = DeployHistoryService.CACHE_KEY)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DeployHistoryServiceImpl extends CommonServiceImpl<DeployHistory> implements DeployHistoryService {

    // private final RedisUtils redisUtils;
    private final DeployHistoryMapper deployHistoryMapper;

    @Override
    public PageInfo<DeployHistoryDto> queryAll(DeployHistoryQueryParam query, Pageable pageable) {
        IPage<DeployHistory> page = PageUtil.toMybatisPage(pageable);
        IPage<DeployHistory> pageList = deployHistoryMapper.selectPage(page, QueryHelpMybatisPlus.getPredicate(query));
        return ConvertUtil.convertPage(pageList, DeployHistoryDto.class);
    }

    @Override
    public List<DeployHistoryDto> queryAll(DeployHistoryQueryParam query){
        return ConvertUtil.convertList(deployHistoryMapper.selectList(QueryHelpMybatisPlus.getPredicate(query)), DeployHistoryDto.class);
    }

    @Override
    public DeployHistory getById(Long id) {
        return deployHistoryMapper.selectById(id);
    }

    @Override
    // @Cacheable(key = "'id:' + #p0")
    public DeployHistoryDto findById(Long id) {
        return ConvertUtil.convert(getById(id), DeployHistoryDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(DeployHistory resources) {
        return deployHistoryMapper.insert(resources) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(DeployHistory resources){
        int ret = deployHistoryMapper.updateById(resources);
        // delCaches(resources.id);
        return ret > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Set<String> ids){
        // delCaches(ids);
        return deployHistoryMapper.deleteBatchIds(ids) > 0;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(String id){
        Set<String> set = new HashSet<>(1);
        set.add(id);
        return this.removeByIds(set);
    }

    /*
    private void delCaches(String id) {
        redisUtils.delByKey(CACHE_KEY + "::id:", id);
    }

    private void delCaches(Set<String> ids) {
        for (String id: ids) {
            delCaches(id);
        }
    }*/

    @Override
    public void download(List<DeployHistoryDto> all, HttpServletResponse response) throws IOException {
      List<Map<String, Object>> list = new ArrayList<>();
      for (DeployHistoryDto deployHistory : all) {
        Map<String,Object> map = new LinkedHashMap<>();
              map.put("应用名称", deployHistory.getAppName());
              map.put("部署日期", deployHistory.getDeployDate());
              map.put("部署用户", deployHistory.getDeployUser());
              map.put("服务器IP", deployHistory.getIp());
              map.put("部署编号", deployHistory.getDeployId());
        list.add(map);
      }
      FileUtil.downloadExcel(list, response);
    }
}
