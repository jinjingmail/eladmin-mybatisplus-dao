package me.zhengjie.modules.mnt.service;

import me.zhengjie.base.BaseService;
import me.zhengjie.modules.mnt.domain.DeploysServers;

import java.util.List;

/**
* @author jinjin
* @date 2020-09-25
*/
public interface DeploysServersService extends BaseService<DeploysServers> {
    List<Long> queryDeployIdByServerId(Long id);
    List<Long> queryServerIdByDeployId(Long id);
    int removeByDeployId(Long id);
    int removeByServerId(Long id);
}
