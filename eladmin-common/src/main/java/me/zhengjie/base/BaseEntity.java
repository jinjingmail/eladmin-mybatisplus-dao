package me.zhengjie.base;

import java.io.Serializable;

/**
 * Created by ThinkPad on 2020-10-06.
 */
public abstract class BaseEntity implements Serializable{
    /* 分组校验 */
    public @interface Create {}

    /* 分组校验 */
    public @interface Update {
    }

}
