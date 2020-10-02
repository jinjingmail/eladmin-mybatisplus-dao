package me.zhengjie.modules.mnt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.zhengjie.base.CommonService;
import me.zhengjie.modules.mnt.domain.DeploysServers;

import java.util.List;

/**
* @author jinjin
* @date 2020-09-25
*/
public interface DeploysServersService extends CommonService<DeploysServers> {
    List<Long> queryDeployIdByServerId(Long id);
    List<Long> queryServerIdByDeployId(Long id);
    int removeByDeployId(Long id);
    int removeByServerId(Long id);
}
