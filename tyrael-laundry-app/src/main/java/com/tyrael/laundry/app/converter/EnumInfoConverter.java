package com.tyrael.laundry.app.converter;

import org.dozer.CustomConverter;

import com.tyrael.laundry.reference.JobItemType;
import com.tyrael.web.dto.EnumInfo;

public class EnumInfoConverter implements CustomConverter {

    @Override
    public Object convert(Object existingDestinationFieldValue,
            Object sourceFieldValue,
            Class<?> destinationClass,
            Class<?> sourceClass) {

        EnumInfo ei = (EnumInfo) existingDestinationFieldValue;
        JobItemType type = (JobItemType) sourceFieldValue;
        ei.setCode(type.name());
        ei.setIconPath(type.getIconPath());

        return ei;
    }

}
