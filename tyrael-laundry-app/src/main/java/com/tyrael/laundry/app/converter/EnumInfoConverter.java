package com.tyrael.laundry.app.converter;

import org.dozer.CustomConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tyrael.laundry.commons.dto.EnumInfo;
import com.tyrael.laundry.reference.JobItemType;

public class EnumInfoConverter implements CustomConverter {

    private static Logger LOG = LoggerFactory.getLogger(EnumInfoConverter.class);

    @Override
    public Object convert(Object existingDestinationFieldValue,
            Object sourceFieldValue,
            Class<?> destinationClass,
            Class<?> sourceClass) {

        EnumInfo ei = (EnumInfo) existingDestinationFieldValue;
        JobItemType type = (JobItemType) sourceFieldValue;
        ei.setCode(type.name());

        return ei;
    }

}
