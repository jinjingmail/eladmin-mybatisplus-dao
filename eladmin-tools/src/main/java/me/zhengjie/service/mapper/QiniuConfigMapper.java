package me.zhengjie.service.mapper;

import me.zhengjie.base.CommonMapper;
import me.zhengjie.domain.QiniuConfig;
import org.springframework.stereotype.Repository;

/**
* @author jinjin
* @date 2020-09-27
*/
@Repository
public interface QiniuConfigMapper extends CommonMapper<QiniuConfig> {

    int updateType(String type);
}
