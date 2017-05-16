package com.emptyshit.hsay.dataTypes;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Time Type Converter
 */
public class TimeTypeConverter implements PropertyConverter<TimeType,Double> {

    /**
     * Converts the Database-Value to TimeType
     * @param databaseValue
     * @return
     */
    @Override
    public TimeType convertToEntityProperty(Double databaseValue) {
        return new TimeType(databaseValue);
    }

    /**
     * Converts TimeType to Database-Value
     * @param entityProperty
     * @return
     */
    @Override
    public Double convertToDatabaseValue(TimeType entityProperty) {
        return entityProperty.getMilliseconds();
    }
}
