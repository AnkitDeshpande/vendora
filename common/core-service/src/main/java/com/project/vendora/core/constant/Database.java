package com.project.vendora.core.constant;

import lombok.Getter;

@Getter
public enum Database {

    /**
     * Default Database.
     */
    DEFAULT_DATABASE("vendora_master");


    /**
     * DB Name.
     */
    private final String dbName;

    Database(String dbName) {
        this.dbName = dbName;
    }

}
