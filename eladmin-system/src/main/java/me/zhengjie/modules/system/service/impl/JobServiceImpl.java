package me.zhengjie.modules.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import me.zhengjie.base.PageInfo;
import me.zhengjie.base.QueryHelpMybatisPlus;
import me.zhengjie.base.impl.CommonServiceImpl;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.exception.EntityExistException;
import me.zhengjie.modules.system.domain.Job;
import me.zhengjie.modules.system.domain.UsersJobs;
import me.zhengjie.modules.system.service.mapper.UsersJobsMapper;
import me.zhengjie.modules.system.service.JobService;
import me.zhengjie.modules.system.service.UsersJobsService;
import me.zhengjie.modules.system.service.dto.JobDto;
import me.zhengjie.modules.system.service.dto.JobQueryParam;
import me.zhengjie.modules.system.service.mapper.JobMapper;
import me.zhengjie.utils.ConvertUtil;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.RedisUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author jinjin
* @date 2020-09-25
*/
@Service
@AllArgsConstructor
@CacheConfig(cacheNames = "job")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JobServiceImpl extends CommonServiceImpl<Job> implements JobService {

    private final JobMapper jobMapper;
    private final UsersJobsMapper usersJobsMapper;
    private final UsersJobsService usersJobsService;
    private final RedisUtils redisUtils;

    @Override
    public PageInfo<JobDto> queryAll(JobQueryParam query, Pageable pageable) {
        IPage<Job> page = PageUtil.toMybatisPage(pageable);
        IPage<Job> pageList = jobMapper.selectPage(page, QueryHelpMybatisPlus.getPredicate(query));
        return ConvertUtil.convertPage(pageList, JobDto.class);
    }

    @Override
    public List<JobDto> queryAll(JobQueryParam query){
        return ConvertUtil.convertList(jobMapper.selectList(QueryHelpMybatisPlus.getPredicate(query)), JobDto.class);
    }

    @Override
    public List<JobDto> queryAll() {
        List<JobDto> list = ConvertUtil.convertList(jobMapper.selectList(Wrappers.emptyWrapper()), JobDto.class);
        redisUtils.set("job::all", list);
        return list;
    }

    @Override
    public Job getById(Long id) {
        return jobMapper.selectById(id);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional
    public boolean save(Job resources) {
        QueryWrapper<Job> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Job::getName, resources.getName());
        Job job = jobMapper.selectOne(queryWrapper);
        if (job != null && ObjectUtil.notEqual(resources.getId(), job.getId())) {
            throw new EntityExistException(Job.class, "name", resources.getName());
        }
        return jobMapper.insert(resources) > 0;
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional
    public boolean updateById(Job resources){
        QueryWrapper<Job> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Job::getName, resources.getName());
        Job job = jobMapper.selectOne(queryWrapper);
        if (job != null && ObjectUtil.notEqual(resources.getId(), job.getId())) {
            throw new EntityExistException(Job.class, "name", resources.getName());
        }
        return jobMapper.updateById(resources) > 0;
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional
    public boolean removeByIds(Set<Long> ids){
        int ret = jobMapper.deleteBatchIds(ids);
        for (Long id: ids) {
            usersJobsService.removeByJobId(id);
        }
        return ret > 0;
    }
    
    @Override
    @Transactional
    public boolean removeById(Long id){
        Set<Long> ids = new HashSet<>(1);
        ids.add(id);
        return this.removeByIds(ids);
    }

    @Override
    public void verification(Set<Long> ids) {
        QueryWrapper<UsersJobs> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(UsersJobs::getUserId, ids);
        int count = usersJobsMapper.selectCount(wrapper);
        if(count > 0){
            throw new BadRequestException("所选的岗位中存在用户关联，请解除关联再试！");
        }
    }

    @Override
    public void download(List<JobDto> all, HttpServletResponse response) throws IOException {
      List<Map<String, Object>> list = new ArrayList<>();
      for (JobDto job : all) {
        Map<String,Object> map = new LinkedHashMap<>();
              map.put("岗位名称", job.getName());
              map.put("岗位状态", job.getEnabled());
              map.put("排序", job.getJobSort());
              map.put("创建者", job.getCreateBy());
              map.put("更新者", job.getUpdateBy());
              map.put("创建日期", job.getCreateTime());
              map.put("更新时间", job.getUpdateTime());
        list.add(map);
      }
      FileUtil.downloadExcel(list, response);
    }
}
