package com.sapling.common.base;

import lombok.Data;

import java.util.Date;

/**
 * basic database table entity
 *
 * @author wei.zhou
 * @date 2019/11/12
 * @since 1.0
 */
@Data
public class BaseDbEntity extends BaseEntity{

    /**
     * the time (Greenwich Mean Time) of record create
     */
    private Date createdTime;

    /**
     * the time (Greenwich Mean Time) of record modified
     */
    private Date modifiedTime;

    /**
     * the identification of user who created the record
     */
    private String createUser;

    /**
     * the identification of user who modified the record
     */
    private String modifiedUser;

}
