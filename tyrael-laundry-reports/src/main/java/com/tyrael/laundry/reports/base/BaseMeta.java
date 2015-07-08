/*
 * Copyright (c) 2014. Medcurial, Inc.
 * All rights reserved.
 */
package com.tyrael.laundry.reports.base;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

/**
 * @author Sofia Ang
 */
@MappedSuperclass
public class BaseMeta {
    @Id
    @Column(name = "ID")
    protected Long id;

    /**
     * Indicates when the extracted row was updated and made effective in the SOURCE table.
     * This should be the created_date of the source entry if the source entry is of version 1 in the reports table.
     */
    @Column(name = "meta_effective_dt")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    protected LocalDateTime metaEffectiveDateTime;

    /**
     * Indicates when row has expired. This should be the meta_effective_dt of the new version of the row.
     */
    @Column(name = "meta_expiration_dt")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    protected LocalDateTime metaExpirationDateTime;

    /**
     * Indicates the revision number of the row.
     */
    @Column(name = "meta_version")
    protected Long metaVersion;

    /**
     * Current version indicator.
     *
     * Kettle type of dimension update: Last version
     *
     * Y, N
     */
    @Column(name = "meta_current_flag", length = 1)
    protected String metaCurrentFlag;

    /**
     * Indicates when the extracted row was inserted in the DESTINATION table.
     *
     * Kettle type of dimension update: Date of last insert
     */
    @Column(name = "meta_created_dt")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    protected LocalDateTime metaCreatedDateTime;

    /**
     * Indicates when the extracted row was last updated in the DESTINATION table.
     *
     * Kettle type of dimension update: Date of last insert or update
     *
     * Particularly useful for flow facts which are updated over time
     * because new updates are not made into new inserts.
     */
    @Column(name = "meta_updated_dt")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    protected LocalDateTime metaUpdatedDt;
}