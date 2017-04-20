package com.emptyshit.hsay.dataTypes;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Created by tungu on 18/04/2017.
 */

public class EmailTypeConverter implements PropertyConverter<EmailType,String> {

    @Override
    public EmailType convertToEntityProperty(String databaseValue) {
        return databaseValue == null ? null : new EmailType(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(EmailType entityProperty) {
        return entityProperty == null ? null : entityProperty.getEmail();
    }
}
