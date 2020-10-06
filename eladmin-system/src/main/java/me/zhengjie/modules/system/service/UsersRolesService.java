package me.zhengjie.modules.system.service;

import me.zhengjie.base.BaseService;
import me.zhengjie.modules.system.domain.UsersRoles;

import java.util.List;

/**
* @author jinjin
* @date 2020-09-25
*/
public interface UsersRolesService extends BaseService<UsersRoles> {
    List<Long> queryUserIdByRoleId(Long id);
    List<Long> queryRoleIdByUserId(Long id);
    int removeByRoleId(Long id);
    int removeByUserId(Long id);


}
