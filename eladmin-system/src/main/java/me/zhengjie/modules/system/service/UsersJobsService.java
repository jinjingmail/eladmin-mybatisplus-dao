package me.zhengjie.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.zhengjie.base.CommonService;
import me.zhengjie.modules.system.domain.UsersJobs;

import java.util.List;

/**
* @author jinjin
* @date 2020-09-25
*/
public interface UsersJobsService extends CommonService<UsersJobs> {
    List<Long> queryUserIdByJobId(Long id);
    List<Long> queryJobIdByUserId(Long id);
    int removeByUserId(Long id);
    int removeByJobId(Long id);
}
