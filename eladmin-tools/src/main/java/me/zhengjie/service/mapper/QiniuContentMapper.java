package me.zhengjie.service.mapper;

import me.zhengjie.base.CommonMapper;
import me.zhengjie.domain.QiniuContent;
import org.springframework.stereotype.Repository;

/**
* @author jinjin
* @date 2020-09-27
*/
@Repository
public interface QiniuContentMapper extends CommonMapper<QiniuContent> {

    QiniuContent findByKey(String key);
}
