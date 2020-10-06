package me.zhengjie.modules.system.service;

import me.zhengjie.base.BaseService;
import me.zhengjie.modules.system.domain.RolesDepts;

import java.util.List;

/**
* @author jinjin
* @date 2020-09-25
*/
public interface RolesDeptsService extends BaseService<RolesDepts> {

    List<Long> queryDeptIdByRoleId(Long id);
    List<Long> queryRoleIdByDeptId(Long id);
    int removeByRoleId(Long id);
    int removeByDeptId(Long id);
}
