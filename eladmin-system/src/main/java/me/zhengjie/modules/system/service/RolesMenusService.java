package me.zhengjie.modules.system.service;

import me.zhengjie.base.BaseService;
import me.zhengjie.modules.system.domain.RolesMenus;

import java.util.List;

/**
* @author jinjin
* @date 2020-09-25
*/
public interface RolesMenusService extends BaseService<RolesMenus> {
    List<Long> queryMenuIdByRoleId(Long id);
    List<Long> queryRoleIdByMenuId(Long id);
    int removeByRoleId(Long id);
    int removeByMenuId(Long id);
}
