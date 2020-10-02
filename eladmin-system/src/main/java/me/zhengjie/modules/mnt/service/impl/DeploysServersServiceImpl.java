package me.zhengjie.modules.mnt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import me.zhengjie.base.impl.CommonServiceImpl;
import me.zhengjie.modules.mnt.domain.DeploysServers;
import me.zhengjie.modules.mnt.mapper.DeploysServersMapper;
import me.zhengjie.modules.mnt.service.DeploysServersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jinjin on 2020-09-25.
 */
@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DeploysServersServiceImpl extends CommonServiceImpl<DeploysServers> implements DeploysServersService {
    private final DeploysServersMapper deploysServersMapper;

    @Override
    public List<Long> queryDeployIdByServerId(Long id) {
        LambdaQueryWrapper<DeploysServers> query = new LambdaQueryWrapper<>();
        query.eq(DeploysServers::getServerId, id);
        return deploysServersMapper.selectList(query).stream().map(DeploysServers::getDeployId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> queryServerIdByDeployId(Long id) {
        LambdaQueryWrapper<DeploysServers> query = new LambdaQueryWrapper<>();
        query.eq(DeploysServers::getDeployId, id);
        return deploysServersMapper.selectList(query).stream().map(DeploysServers::getServerId)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeByDeployId(Long id) {
        LambdaUpdateWrapper<DeploysServers> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(DeploysServers::getDeployId, id);
        return deploysServersMapper.delete(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeByServerId(Long id) {
        LambdaUpdateWrapper<DeploysServers> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(DeploysServers::getServerId, id);
        return deploysServersMapper.delete(wrapper);
    }
}
