package me.zhengjie.utils;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import me.zhengjie.base.PageInfo;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 转换
 * Created by jinjin on 2020-09-22.
 */
public class ConvertUtil {
    public static <T, S> T convert(final S s, Class<T> clz) {
        return s == null ? null : BeanUtil.copyProperties(s, clz);
    }

    public static <T, S> List<T> convertList(List<S> s, Class<T> clz) {
        return s == null ? null : s.stream().map(vs -> BeanUtil.copyProperties(vs, clz)).collect(Collectors.toList());
    }

    public static <T, S> Set<T> convertSet(Set<S> s, Class<T> clz) {
        return s == null ? null : s.stream().map(vs -> BeanUtil.copyProperties(vs, clz)).collect(Collectors.toSet());
    }

    public static <T, S> PageInfo<T> convertPage(IPage<S> page, Class<T> clz) {
        if (page == null) {
            return null;
        }
        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setTotalElements(page.getTotal());
        pageInfo.setContent(convertList(page.getRecords(), clz));
        return pageInfo;
    }

}
