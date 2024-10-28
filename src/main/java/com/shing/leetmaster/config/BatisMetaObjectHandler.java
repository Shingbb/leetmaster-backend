package com.shing.leetmaster.config;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author shing
 */
@Slf4j
@Component
public class BatisMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入填充方法，用于在插入数据前自动填充某些字段
     * @param metaObject 待插入的元对象，通过该对象进行字段填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 插入操作开始时记录日志
        log.info("开始插入填充...");
        // 检查元对象是否包含userId字段，以确定是否需要填充该字段
        if (metaObject.hasGetter("userId")) {
            try {
                // 尝试填充userId字段，使用当前登录用户的ID
                this.strictInsertFill(metaObject, "userId", Long.class, Long.valueOf(StpUtil.getLoginId().toString()));
            } catch (Exception e) {
                // 如果填充过程中发生异常，记录错误日志
                log.error("插入填充失败：{}", e.getMessage());
            }
        }
        // 填充创建时间字段和更新时间字段
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
    }

    /**
     * 更新填充方法，用于在更新数据前自动填充某些字段
     * @param metaObject 待更新的元对象，通过该对象进行字段填充
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新操作开始时记录日志
        log.info("开始更新填充...");
        // 此处可以添加更新填充的逻辑，本示例中未具体实现
        // 自动填充审核人ID和审核时间
        if (metaObject.hasGetter("reviewerId")) {
            try {
                this.strictUpdateFill(metaObject, "reviewerId", Long.class, Long.valueOf(StpUtil.getLoginId().toString()));
            } catch (Exception e) {
                log.error("更新填充失败：{}", e.getMessage());
            }
        }
        // 如果审核时间字段存在，则填充审核时间
        if (metaObject.hasGetter("reviewTime")) {
            this.strictUpdateFill(metaObject, "reviewTime", Date.class, new Date());
        }
    }

}
