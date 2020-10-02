package me.zhengjie.modules.mnt.service.dto;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.zhengjie.base.CommonDto;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
* @author jinjin
* @date 2020-09-27
*/
@Getter
@Setter
@NoArgsConstructor
public class DeployDto extends CommonDto implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long id;

    private Long appId;
    private AppDto app;

    /**
     * 服务器
     */
    private Set<ServerDto> deploys;

    private String servers;

    /**
     * 服务状态
     */
    private String status;

    public String getServers() {
        if(CollectionUtil.isNotEmpty(deploys)){
            return deploys.stream().map(ServerDto::getName).collect(Collectors.joining(","));
        }
        return servers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeployDto deployDto = (DeployDto) o;
        return Objects.equals(id, deployDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
