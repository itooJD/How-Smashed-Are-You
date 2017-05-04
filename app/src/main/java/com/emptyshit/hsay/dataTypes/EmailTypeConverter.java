package com.emptyshit.hsay.dataTypes;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Email Type Converter
 */
public class EmailTypeConverter implements PropertyConverter<EmailType,String> {

    /**
     * Converts the Database-Value to EmailType
     * @param databaseValue
     * @return
     */
    @Override
    public EmailType convertToEntityProperty(String databaseValue) {
        return databaseValue == null ? null : new EmailType(databaseValue);
    }

    /**
     * Converts EmailType to Database-Value
     * @param entityProperty
     * @return
     */
    @Override
    public String convertToDatabaseValue(EmailType entityProperty) {
        return entityProperty == null ? null : entityProperty.getEmail();
    }
}
