package me.zhengjie.base;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.annotation.DataPermission;
import me.zhengjie.annotation.Query;
import me.zhengjie.utils.SecurityUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author Zheng Jie
 * @date 2019-6-4 14:59:48
 */
@Slf4j
public class QueryHelpMybatisPlus {

    // TODO DataPermission
    public static <R, Q> QueryWrapper<R> getPredicate(Q query) {
        QueryWrapper<R> queryWrapper = new QueryWrapper<>();
        if (query == null) {
            return queryWrapper;
        }

        // 数据权限验证
        DataPermission permission = query.getClass().getAnnotation(DataPermission.class);
        if(permission != null){
            // 获取数据权限
            List<Long> dataScopes = SecurityUtils.getCurrentUserDataScope();
            if(CollectionUtil.isNotEmpty(dataScopes)){
                if(StringUtils.isNotBlank(permission.joinName()) && StringUtils.isNotBlank(permission.fieldName())) {
                    //Join join = root.join(permission.joinName(), JoinType.LEFT);
                    //list.add(getExpression(permission.fieldName(),join, root).in(dataScopes));
                    throw new RuntimeException("未实现");
                } else if (StringUtils.isBlank(permission.joinName()) && StringUtils.isNotBlank(permission.fieldName())) {
                    //list.add(getExpression(permission.fieldName(),null, root).in(dataScopes));
                    queryWrapper.in(permission.fieldName(), dataScopes);
                }
            }
        }

        try {
            List<Field> fields = getAllFields(query.getClass(), new ArrayList<>());
            for (Field field : fields) {
                boolean accessible = field.isAccessible();
                field.setAccessible(true);
                Query q = field.getAnnotation(Query.class);
                if (q != null) {
                    String propName = q.propName();
                    String blurry = q.blurry();
                    String attributeName = StrUtil.isBlank(propName) ? field.getName() : propName;
                    attributeName = humpToUnderline(attributeName);
                    Class<?> fieldType = field.getType();
                    Object val = field.get(query);
                    if (ObjectUtil.isNull(val) || "".equals(val)) {
                        continue;
                    }
                    // 模糊多字段
                    if (ObjectUtil.isNotEmpty(blurry)) {
                        String[] blurrys = blurry.split(",");
                        //queryWrapper.or();
                        queryWrapper.and(wrapper -> {
                            for (String blurry1 : blurrys) {
                                String column = humpToUnderline(blurry1);
                                //if(i!=0){
                                wrapper.or();
                                //}
                                wrapper.like(column, val.toString());
                            }
                        });
                        continue;
                    }
                    String finalAttributeName = attributeName;
                    switch (q.type()) {
                        case EQUAL:
                            //queryWrapper.and(wrapper -> wrapper.eq(finalAttributeName, val));
                            queryWrapper.eq(attributeName, val);
                            break;
                        case GREATER_THAN:
                           queryWrapper.ge(finalAttributeName, val);
                            break;
                        case LESS_THAN:
                            queryWrapper.le(finalAttributeName, val);
                            break;
                        case LESS_THAN_NQ:
                           queryWrapper.lt(finalAttributeName, val);
                            break;
                        case INNER_LIKE:
                           queryWrapper.like(finalAttributeName, val);
                            break;
                        case LEFT_LIKE:
                           queryWrapper.likeLeft(finalAttributeName, val);
                            break;
                        case RIGHT_LIKE:
                           queryWrapper.likeRight(finalAttributeName, val);
                            break;
                        case IN:
                            if (CollUtil.isNotEmpty((Collection<Long>) val)) {
                               queryWrapper.in(finalAttributeName, (Collection<Long>) val);
                            }
                            break;
                        case NOT_EQUAL:
                           queryWrapper.ne(finalAttributeName, val);
                            break;
                        case NOT_NULL:
                            queryWrapper.isNotNull(finalAttributeName);
                            break;
                        case IS_NULL:
                            queryWrapper.isNull(finalAttributeName);
                            break;
                        case BETWEEN:
                            List<Object> between = new ArrayList<>((List<Object>) val);
                           queryWrapper.between(finalAttributeName, between.get(0), between.get(1));
                            break;
                        default:
                            break;
                    }
                }
                field.setAccessible(accessible);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return queryWrapper;
    }

    public static List<Field> getAllFields(Class clazz, List<Field> fields) {
        if (clazz != null) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            getAllFields(clazz.getSuperclass(), fields);
        }
        return fields;
    }

    /***
     * 驼峰命名转为下划线命名
     *
     * @param para
     *        驼峰命名的字符串
     */

    public static String humpToUnderline(String para) {
        StringBuilder sb = new StringBuilder(para);
        int temp = 0;
        if (!para.contains("_")) {
            for (int i = 0; i < para.length(); i++) {
                if (Character.isUpperCase(para.charAt(i))) {
                    sb.insert(i + temp, "_");
                    temp += 1;
                }
            }
        }
        return sb.toString();
    }

//    public static void main(String[] args) {
//        QueryWrapper<Paging> query = new QueryWrapper<Paging>();
//        //query.or();
//        query.or(wrapper -> wrapper.eq("store_id", 1).or().eq("store_id", 2));
//        //query.like("a",1);
//        //query.or();
//        //query.like("b",2);
//        //query.and(wrapper->wrapper.eq("c",1));
//        query.eq("1", 1);
//
//        System.out.println(query.getSqlSegment());
//    }
}
