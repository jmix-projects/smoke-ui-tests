package com.company.samplesales.entity;

import io.jmix.appsettings.defaults.AppSettingsDefault;
import io.jmix.appsettings.entity.AppSettingsEntity;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@JmixEntity
@Table(name = "SALES_CUSTOM_SETTINGS")
@Entity(name = "sales_CustomSettings")
public class CustomSettings extends AppSettingsEntity {
    @Column(name = "NOTIFICATION_TEXT")
    private String notificationText;

    @AppSettingsDefault("B")
    @Column(name = "DEFAULT_GRADE")
    private String defaultGrade;

    public String getDefaultGrade() {
        return defaultGrade;
    }

    public void setDefaultGrade(String defaultGrade) {
        this.defaultGrade = defaultGrade;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }
}