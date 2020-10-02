package me.zhengjie.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.zhengjie.base.CommonService;
import me.zhengjie.modules.system.domain.RolesDepts;

import java.util.List;

/**
* @author jinjin
* @date 2020-09-25
*/
public interface RolesDeptsService extends CommonService<RolesDepts> {

    List<Long> queryDeptIdByRoleId(Long id);
    List<Long> queryRoleIdByDeptId(Long id);
    int removeByRoleId(Long id);
    int removeByDeptId(Long id);
}
