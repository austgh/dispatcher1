package com.dispatcher.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * @author think
 * @version 1.0
 * @date 2022/3/11 11:16
 */
public class WepmfHelpMe {
    @JacksonXmlProperty(localName = "ServiceId")
    private String serviceId;
    @JacksonXmlProperty(localName = "Sql")
    private String sql;
    @JacksonXmlProperty(localName = "DbType")
    private String dbType;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    @Override
    public String toString() {
        return "WepmfHelpMe{" +
                "serviceId='" + serviceId + '\'' +
                ", sql='" + sql + '\'' +
                ", dbType='" + dbType + '\'' +
                '}';
    }
}
